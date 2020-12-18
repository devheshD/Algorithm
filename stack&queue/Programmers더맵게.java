package com.programmers.java;

import java.util.PriorityQueue;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] scoville = {1, 2, 3, 9, 10, 12};
		int K = 7;
		
		s.solution(scoville, K);
	
	}
	
	public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int num : scoville) pq.add(num);
        
        boolean flag = false;
        // 맨앞의 음식의 스코빌 지수가 K이상이 될때까지 반복
        while (pq.size() >= 2) {
        	int food1 = pq.poll();
        	int food2 = pq.poll();
        	int newFood = food1 + (food2 * 2);
        	pq.add(newFood);
        	answer++;
        	
        	if (pq.peek() >= K) {
        		flag = true;
        		break;
        	}
        	
        }
        // 스코빌 지수가 K이상 안되는 경우
        if (!flag)
        	answer = -1;
        
        return answer;
    }

}
