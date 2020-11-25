package com.programmers.java;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int[] prices = {1, 2, 3, 2, 3};
		s.solution(prices);
	
	}
	
	public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        for (int i = 0; i < prices.length; i++) {
        	int cnt = 0;
        	
        	for (int j = i + 1; j < prices.length; j++) {
        		if (prices[i] <= prices[j]) {
					cnt++;
				} else {
					cnt++;
					break;
				}
        	}
        	
        	answer[i] = cnt;
        }
        
        return answer;
    }
	
	
}
