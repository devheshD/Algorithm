package com.programmers.java;

import java.util.Stack;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		
		String number = "1924";
		int k = 2;
		
		s.solution(number, k);
	
	}
	
	public String solution(String number, int k) {
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (k == 0) {
                stack.push(ch);
            } else {
                while (!stack.isEmpty() && k != 0) {
                    if (stack.peek() >= ch) {
                        break;
                    } else {
                        stack.pop();
                        k--;
                    }
                }
            
                stack.push(ch);
                
            }
                
        }
        
        StringBuilder sb = new StringBuilder();
        int len = stack.size();
        if (k != 0) {
            len -= k;    
        } 
        
        for (int i = 0; i < len; i++) {
            sb.append(stack.get(i));
        }    

        String answer = sb.toString();
        
        return answer;
    
	}
	

}
