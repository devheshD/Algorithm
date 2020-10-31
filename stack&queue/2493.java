package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StackInfo {
	long index, height;
	
	public StackInfo(long index, long height) {
		this.index = index;
		this.height = height;
	}
}

public class Main {
	static int N;
	static long[] height;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());	// 탑의 개수
    	List<Integer> answer = new ArrayList<Integer>();
    	// 탑의 높이 정보 입력
    	String[] input = br.readLine().split(" ");
    	height = new long[N];					
    	for (int i = 0; i < N; i++) {
    		height[i] = Integer.parseInt(input[i]);
    	}
    	Stack<StackInfo> stack = new Stack<StackInfo>();
    	stack.add(new StackInfo(1, height[0]));
    	// 맨 왼쪽의 탑은 수신받을 곳이 없기 때문에 0저장
    	answer.add(0);
    	boolean flag = true;
    	
    	
    	for (int i = 1; i < N; i++) {
    		while (!stack.isEmpty()) {
    			StackInfo info = stack.peek();
    			// 스택에 들어있는 탑의 높이보다 현재 인덱스의 탑의 높이가 높은 경우
    			if (info.height <= height[i]) {
    				stack.pop();
    				flag = true;
    			} else {
    				// 반대인경우 스택에 들어있는 탑이 위치했던 인덱스 저장
    				answer.add((int) info.index);
    				stack.push(new StackInfo(i + 1, height[i]));
    				flag = false;
    				break;
    			}
    			
    		}
    		
     		if (flag) {
     			answer.add(stack.size());
     			stack.push(new StackInfo(i + 1, height[i]));
     		}
    		
    	}

    	for (int i = 0; i < N; i++) {
    		System.out.print(answer.get(i) + " ");
    	}	
    	
	}

}
