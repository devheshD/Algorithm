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
    	N = Integer.parseInt(br.readLine());	// ?‘ì˜ ê°œìˆ˜
    	List<Integer> answer = new ArrayList<Integer>();
    	// ?‘ì˜ ?’ì´ ?•ë³´ ?…ë ¥
    	String[] input = br.readLine().split(" ");
    	height = new int[N];					
    	for (int i = 0; i < N; i++) {
    		height[i] = Integer.parseInt(input[i]);
    	}
    	Stack<StackInfo> stack = new Stack<StackInfo>();
    	stack.add(new StackInfo(1, height[0]));
    	// ë§??¼ìª½???‘ì? ?˜ì‹ ë°›ì„ ê³³ì´ ?†ê¸° ?Œë¬¸??0?€??
    	answer.add(0);
    	boolean flag = true;
    	
    	
    	for (int i = 1; i < N; i++) {
    		while (!stack.isEmpty()) {
    			StackInfo info = stack.peek();
    			// ?¤íƒ???¤ì–´?ˆëŠ” ?‘ì˜ ?’ì´ë³´ë‹¤ ?„ì¬ ?¸ë±?¤ì˜ ?‘ì˜ ?’ì´ê°€ ?’ì? ê²½ìš°
    			if (info.height <= height[i]) {
    				stack.pop();
    				flag = true;
    			} else {
    				// ë°˜ë??¸ê²½???¤íƒ???¤ì–´?ˆëŠ” ?‘ì´ ?„ì¹˜?ˆë˜ ?¸ë±???€??
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
