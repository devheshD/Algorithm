package com.programmers.java;

import java.util.Collections;
import java.util.PriorityQueue;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] priorities = {1, 1, 9, 1, 1, 1};
		int location = 0;
		
		s.solution(priorities, location);
	
	}
	
	public int solution(int[] priorities, int location) {
        int answer = 1;
        int len = priorities.length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
         for (int i = 0; i < priorities.length; i++) {
        	// 우선순위 큐에 문서 우선순위 담기
        	pq.add(priorities[i]);
        }
         
        loop:
        while (!pq.isEmpty()) {
        	for (int i = 0; i < len; i++) {
        		// 값이 일치하는 경우
        		if (pq.peek() == priorities[i]) {
        			// pq의 위치와 초기 문서의 위치가 일치할 경우
        			if (i == location) {
        				break loop;
        			}
        			answer++;
        			pq.poll();
        		} 
        	}

        }
        
        return answer;
    
	}
	
}
