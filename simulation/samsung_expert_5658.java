package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class Solution {
    static int N, K;
    
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    int tc = Integer.parseInt(br.readLine());
	    
	    for (int test_case = 1; test_case <= tc; test_case++) {
	    	String[] sizeInput = br.readLine().split(" ");
	    	N = Integer.parseInt(sizeInput[0]);			// 숫자의 개수
	    	K = Integer.parseInt(sizeInput[1]);			// 크기 순서
	    	String[] lock = br.readLine().split("");		// 자물쇠	    	
	    	HashSet<String> set = new HashSet<String>();
	    	
	    	for (int i = 0; i < N / 4; i++) {
	    		// 영역을 나눠 set에 저장
	    		save(lock, set);
	    		// 비밀번호 한바퀴 돌리기
	    		rotate(lock);
	    	}
	    	// 정렬을 위해 set에 저장된 값 저장
	    	List<String> list = new ArrayList<String>();
	    	for (String key : set) {
	    		list.add(key);
	    	}
	    	// compare메서드를 이용해 내림차순으로 정렬
	    	Collections.sort(list, new Comparator<String>() {
				@Override
				public int compare(String o1, String o2) {
					// TODO Auto-generated method stub
					return (o2 + o1).compareTo(o1 + o2);
				}
			});
	    	// K - 1번째의 숫자를 가져와 16진수로 변환
	    	String number = list.get(K - 1);
	    	int answer = Integer.parseInt(number, 16);
	    	
	    	bw.write("#" + test_case + " " + answer);
	    	bw.newLine();
	    }
	
	    bw.flush();
	    bw.close();
	
	}

	private static void rotate(String[] lock) {
		String last = lock[lock.length - 1];
		// 뒤에서부터 앞으로 밀어내기
		for (int i = lock.length - 2; i >= 0; i--) {
			lock[i + 1] = lock[i];
		}
		
		lock[0] = last;
		
	}

	private static void save(String[] lock, HashSet<String> set) {
		StringBuilder sb = new StringBuilder();
		int num = N / 4;
		int cnt = 0;
		
		for (int i = 0; i < lock.length; i++) {
			sb.append(lock[i]);
			cnt++;
			if (cnt == num) {
				set.add(sb.toString());
				cnt = 0;
				sb = new StringBuilder();
			}
		}
		
	}
	

}
	