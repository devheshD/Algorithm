package com.programmers.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] progresses = {95, 90, 99, 99, 80, 99};
		int[] speeds = {1, 1, 1, 1, 1, 1};
		
		s.solution(progresses, speeds);
	
	}
	
	public List<Integer> solution(int[] progresses, int[] speeds) {
		List<Integer> answer = new ArrayList<Integer>();
        int[] days = new int[progresses.length];
        
        for (int i = 0; i < progresses.length; i++) {
        	// 남은 작업량
        	int remain = 100 - progresses[i];
        	// 작업을 끝낼수 있는 날짜 계산
        	days[i] = (remain % speeds[i] == 0) ? remain / speeds[i] : (remain / speeds[i]) + 1;
        	
        }
        
        Stack<Integer> stack = new Stack<Integer>();
        for (int day : days) {
        	if (stack.isEmpty()) {
        		stack.push(day);
        		continue;
        	}
        	
        	if (stack.get(0) < day) {
        		answer.add(stack.size());
        		stack.clear();
        	}
        	
        	stack.push(day);
        	
        }
        
        answer.add(stack.size());

        return answer;
    }
	
}
