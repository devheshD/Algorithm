package com.programmers.java;


public class Solution {
	static int answer;
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		int[] numbers = {1, 1, 1, 1, 1};
		int target = 3;
		
		s.solution(numbers, target);
	
	}
	
	public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0);
        
        return answer;
    }

	private void dfs(int[] numbers, int target, int index) {
		if (index == numbers.length) {
			for (int i = 0; i < numbers.length; i++) {
				System.out.print(numbers[i] + " ");
			}
			System.out.println();
			
			int sum = 0;
			for (int num : numbers) sum += num;
			
			if (sum == target)
				answer++;
			
			return;
		}
		
		numbers[index] *= 1;
		dfs(numbers, target, index + 1);
		
		numbers[index] *= -1;
		dfs(numbers, target, index + 1);
		
	}

}
