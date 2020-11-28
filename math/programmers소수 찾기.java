package com.programmers.java;

import java.util.HashSet;

public class Solution {
	static String[] array, select;
	static boolean[] check;
	static HashSet<Integer> set;
	static StringBuilder sb;
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		String numbers = "011";
		
		s.solution(numbers);
	
	}
	
	public int solution(String numbers) {
        array = numbers.split("");
        check = new boolean[array.length];
        set = new HashSet<Integer>();
        
        for (int selectCnt = 1; selectCnt <= array.length; selectCnt++) {
        	select = new String[selectCnt];
        	permutation(0, selectCnt);
        }

        int answer = set.size();
        
        return answer;
    }

	private void permutation(int cnt, int selectCnt) {
		if (cnt == selectCnt) {
			sb = new StringBuilder();
			for (int i = 0; i < select.length; i++) {
				sb.append(select[i]);
			}
			
			int number = Integer.parseInt(sb.toString());
			seachDecimal(number);
			return;
		}
		
		for (int index = 0; index < array.length; index++) {
			if (!check[index]) {
				check[index] = true;
				select[cnt] = array[index];
				permutation(cnt + 1, selectCnt);
				check[index] = false;
			}
		}
		
	}

	private void seachDecimal(int number) {
		boolean flag = true;

		// number가 2인 경우
		if (number == 2) {
			set.add(number);
			return;
		}
		
		if (number > 2) {
			for (int i = 2; i < number - 1; i++) {
				if (number % i == 0) {
					flag = false;
					break;
				}
			}
			// 소수인 경우 HashSet에 저장
			if (flag) {
				set.add(number);
			}
		}

	}
	
}
