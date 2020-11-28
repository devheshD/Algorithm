package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	static boolean[] alphabet;
	static ArrayList<String> list;
	static int cnt, answer;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] count = br.readLine().split(" ");
    	int N = Integer.parseInt(count[0]);
    	int K = Integer.parseInt(count[1]);
    	if (K <= 4) {
    		System.out.println("0");
    		return;
    	}
    	
    	alphabet = new boolean[26];	
    	list = new ArrayList<String>();
    	
    	alphabet['a' - 97] = true; 
    	alphabet['n' - 97] = true; 
    	alphabet['t' - 97] = true; 
    	alphabet['i' - 97] = true; 
    	alphabet['c' - 97] = true; 
    	
    	for (int i = 0; i < N; i++) {
    		String input = br.readLine();
    		input = input.replace("anta", "");
    		input = input.replace("tica", "");
    		list.add(input);
    	}
    	
    	// 이미 5개의 알파벳은 배웠으므로
    	cnt = K - 5;
    	
    	comb(0, 0);
    	System.out.println(answer);
    }

	private static void comb(int start, int count) {
		if (count == cnt) {
			search();
			return;
		}
		
		for (int i = start; i < 26; i++) {
			if (skip(i) && !alphabet[i]) {
				alphabet[i] = true;
				comb(i + 1, count + 1);
				alphabet[i] = false;
			}
		}
	}

	private static void search() {
		int answer_cnt = 0;
		
		for (int i = 0; i < list.size(); i++) {
			boolean flag = false;
			String str = list.get(i);                                                                                                                                                                                                                   
			
			for (int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				if (!alphabet[ch - 97]) {
					flag = true;
					break;
				}
			}
			
			if (!flag) {
				answer_cnt++;
			}
			
		}
		
		answer = Math.max(answer_cnt, answer);
		
	}

	private static boolean skip(int i) {
		if (i == ('a' - 97) || i == ('n' - 97) || i == ('t' - 97) || 
			i == ('i' - 97) || i == ('c' - 97)) {
			return false;
		}
		
		return true;
	}

}