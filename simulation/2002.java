package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static String[] start;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	start = new String[N + 1];

    	// 터널 진입 차량
    	for (int i = 1; i <= N; i++) {
    		String input = br.readLine();
    		start[i] = input;
    	}
    	
    	int[] rank = new int[N + 1];
    	int num = 0;
    	// 터널 통과 차량
    	for (int i = 1; i <= N; i++) {
    		String input = br.readLine();
    		// 터널 통과 차량의 순위
    		for (int j = 1; j <= N; j++) {
    			if (input.equals(start[j])) {
    				num = j;
    			}
    		}
    		
    		rank[i] = num;
    	}
    	
    	int answer = 0;
    	for (int i = 1; i < N; i++) {
    		boolean flag = false;
    		
    		for (int j = i + 1; j <= N; j++) {
    			if (rank[i] > rank[j]) {
    				flag = true;
    				break;
    			}
    		}
    		
    		if (flag)
    			answer++;
    		
    	}
    	
    	System.out.println(answer);
    	
	}

}
