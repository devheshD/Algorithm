package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int n = Integer.parseInt(br.readLine());
    	long[] building = new long[n];
    	
    	for (int i = 0; i < n; i++) {
    		long num = Long.parseLong(br.readLine());
    		building[i] = num;
    	}
    	
    	Stack<Long> stack = new Stack<Long>();
    
    	long answer = 0;
    	for (int i = 0; i < n; i++) {
    		while (!stack.isEmpty()) {
    			// 현재 높이보다  다음 빌딩의 높이가 높을떄 까지 pop
    			if (stack.peek() <= building[i]) {
    				stack.pop();
    			} else {
    				break;
    			}
    			
    		}
    		System.out.println(stack);
    		answer += stack.size();
    		stack.push(building[i]);
    	}
    	
    	System.out.println(answer);
    	
	}

}
