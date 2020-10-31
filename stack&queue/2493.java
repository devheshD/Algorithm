package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class StackInfo {
	int index, height;
	
	public StackInfo(int index, int height) {
		this.index = index;
		this.height = height;
	}
}

public class Main {
	static int N;
	static int[] height;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());	// ?�의 개수
    	List<Integer> answer = new ArrayList<Integer>();
    	// ?�의 ?�이 ?�보 ?�력
    	String[] input = br.readLine().split(" ");
    	height = new int[N];					
    	for (int i = 0; i < N; i++) {
    		height[i] = Integer.parseInt(input[i]);
    	}
    	Stack<StackInfo> stack = new Stack<StackInfo>();
    	stack.add(new StackInfo(1, height[0]));
    	// �??�쪽???��? ?�신받을 곳이 ?�기 ?�문??0?�??
    	answer.add(0);
    	boolean flag = true;
    	
    	
    	for (int i = 1; i < N; i++) {
    		while (!stack.isEmpty()) {
    			StackInfo info = stack.peek();
    			// ?�택???�어?�는 ?�의 ?�이보다 ?�재 ?�덱?�의 ?�의 ?�이가 ?��? 경우
    			if (info.height <= height[i]) {
    				stack.pop();
    				flag = true;
    			} else {
    				// 반�??�경???�택???�어?�는 ?�이 ?�치?�던 ?�덱???�??
    				answer.add(info.index);
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
