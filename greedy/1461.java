package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static String[] input;
	static int N, M, answer, max;
	static List<Integer> minus, plus;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 책의 개수
		M = Integer.parseInt(input[1]);		// 한번에 들 수 있는 책의 개수

		input = br.readLine().split(" ");
		plus = new ArrayList<Integer>();
		minus = new ArrayList<Integer>();
		for (int i = 0; i < input.length; i++) {
			int num = Integer.parseInt(input[i]);
			
			if (max < Math.abs(num)) {
				max = Math.abs(num);
			}
			
			if (num > 0) {
				plus.add(num);
			} else {
				minus.add(Math.abs(num));
			}
			
		}
		
		Collections.sort(plus, Collections.reverseOrder());
		Collections.sort(minus, Collections.reverseOrder());
		
		for (int i = 0; i < plus.size(); i++) {
			// 가장 멀리 있는 곳일 경우  
			if (i % M == 0 && plus.get(i) == max) {
				answer += plus.get(i);
			} 
			// 최대한 책을 많이 가지고 꽂는 위치
			else if (i % M == 0) {
				answer += (plus.get(i) * 2);
			}
		}
		
		for (int i = 0; i < minus.size(); i++) {
			if (i % M == 0 && minus.get(i) == max) {
				answer += minus.get(i);
			} else if (i % M == 0) {
				answer += (minus.get(i) * 2);
			}
		}
		
		System.out.println(answer);
		
	}

	
}

