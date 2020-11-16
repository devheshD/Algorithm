package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, answer;
	static String[] input;	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		input = br.readLine().split("");
		
		for (int i = 0; i < N - 1; i++) {
			if (input[i].equals("E") && input[i + 1].equals("W")) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}


}