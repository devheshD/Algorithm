package com.programmers.java;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		int[] numbers = {6, 10, 2};
		
		s.solution(numbers);
	
	}
	
	public String solution(int[] numbers) {
		String answer = "";
		String[] strArray = new String[numbers.length];
		for (int i = 0; i < strArray.length; i++) {
			strArray[i] = Integer.toString(numbers[i]);
		}
		
		Arrays.sort(strArray, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2 + o1).compareTo(o1 + o2);
			}
		});
		
		if (strArray[0].equals("0")) {
            return "0";
        }
	
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strArray.length; i++) {
			sb.append(strArray[i]);
		}
		answer = sb.toString();
		
		return answer;
    }

}
