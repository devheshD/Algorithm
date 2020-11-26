package com.programmers.java;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		String skill = "CBD";
		String[] skill_trees = {"BACDE", "CBADF", "AECB", "BDA"};
		
		s.solution(skill, skill_trees);
	
	}
	
	public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        boolean[] alphabet = new boolean[26];
        for (int i = 0; i < skill.length(); i++) {
        	alphabet[skill.charAt(i) - 'A'] = true;
        }
        
        for (String trees : skill_trees) {
        	StringBuilder sb = new StringBuilder();
        	
        	for (int i = 0; i < trees.length(); i++) {
        		if (alphabet[trees.charAt(i) - 'A']) {
        			sb.append(trees.charAt(i));
        		}
		}
		
        	if (compare(sb, skill)) {
        		answer++;
        	}
        	
        }
        
        return answer;
    }

	private boolean compare(StringBuilder sb, String skill) {
		for (int i = 0; i < sb.toString().length(); i++) {
			if (sb.toString().charAt(i) != skill.charAt(i)) {
				return false;
			}
		}
		
		return true;
	}
	
}
