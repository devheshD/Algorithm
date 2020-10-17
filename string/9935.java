package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String str = br.readLine();
    	String bomb = br.readLine();
    	Stack<Character> stack = new Stack<Character>();    	
    	
    	for (int i = 0; i < str.length(); i++) {
    		stack.push(str.charAt(i));
    		int bomb_index = bomb.length() - 1;
			int stack_index = stack.size() - 1;
			
    		// 폭발 문자열의 끝과 일치할 경우
			// 스택의 사이즈가 폭발 문자열의 길이보다 크거나 같아야 한다.
			if (stack.size() >= bomb.length() && stack.peek() == bomb.charAt(bomb_index)) {
    			boolean flag = true;
    			// 폭발 문자열 뒤부터 체크
    			for (int j = 0; j < bomb.length(); j++) {
    				if (bomb.charAt(bomb_index) != stack.get(stack_index)) {
    					flag = false;
    					break;
    				}
    				
    				bomb_index--;
    				stack_index--;
    			}
    			// 완전히 일치할 경우 스택에서 제거
    			if (flag) {
        			for (int j = 0; j < bomb.length(); j++) {
        				stack.pop();
        			}
        		}
    		}
    		
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	if (stack.size() == 0) {
    		System.out.println("FRULA");
    	} else {
    		for (int i = 0; i < stack.size(); i++) {
    			sb.append(stack.get(i));
    		}
    		System.out.println(sb.toString());
    	}
    	
    }


}