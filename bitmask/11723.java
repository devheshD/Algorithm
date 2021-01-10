package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	final static int NUM_SIZE = 20;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int S = 0;
		int num = 0;
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < M; i++) {
			String[] input = br.readLine().split(" ");
			
			switch (input[0]) {
			case "add":
				num = Integer.parseInt(input[1]);
				S = S | (1 << num - 1);
				break;
			case "remove":
				num = Integer.parseInt(input[1]);
				S = S & ~(1 << num - 1);
				break;
			case "check":
				num = Integer.parseInt(input[1]);
				sb.append((S & (1 << (num - 1))) != 0 ? "1\n" : "0\n");
				break;
			case "toggle":
				num = Integer.parseInt(input[1]);
				S = S ^ (1 << num - 1);
				break;
			case "all":
				S = ((1 << NUM_SIZE) - 1);
				break;
			case "empty":
				S &= 0;
				break;
			}
		}
		
		System.out.println(sb.toString());
		
	}

}

