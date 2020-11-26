package com.programmers.java;

public class Solution {
	
	public static void main(String[] args) {
		Solution s = new Solution();
		int w = 8;
		int h = 12;
		
		s.solution(w, h);
	
	}
	
	public long solution(int w, int h) {
        long answer = 1;
        long lw = (long) w;
        long lh = (long) h;
        // 전체 넓이
        long extent = lw * lh;
        // 사용 할 수 없는 정사각형 개수 = (가로 + 세로) - 최대공약수
        long cutSquare = (lw + lh) - gcd(lw, lh);
        
        answer = extent - cutSquare;
        
        return answer;
    
	}

	private long gcd(long w, long h) {
		if (h == 0) {
			return w;
		}
		
		return gcd(h, w % h);
	}
	
	
}
