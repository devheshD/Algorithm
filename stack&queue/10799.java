package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Character> stack = new Stack<Character>();
		String input = br.readLine();
		input = input.replace("()", "*");
		
		int answer = 0;
		
		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			
			if (ch == '*') {
				answer += stack.size();
			} else if (ch == '(') {
				stack.push(ch);
			} else {
				stack.pop();
				answer += 1;
			}
			
		}
		
		System.out.println(answer);
		
	}

}

