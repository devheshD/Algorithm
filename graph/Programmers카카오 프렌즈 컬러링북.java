package com.programmers.java;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] check;
	public static void main(String[] args) {
		Solution s = new Solution();
		int m = 6;
		int n = 4;
		int[][] picture = {{1, 1, 1, 0},
						   {1, 2, 2, 0},
						   {1, 0, 0, 1},
						   {0, 0, 0, 1},
						   {0, 0, 0, 3},
						   {0, 0, 0, 3}};
		
		s.solution(m, n, picture);
	
	}
	
	public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        check = new boolean[m][n];
        
        for (int row = 0; row < m; row++) {
        	for (int col = 0; col < n; col++) {
        		if (!check[row][col] && picture[row][col] != 0) {
        			check[row][col] = true;
        			int cnt = bfs(row, col, m, n, picture[row][col], picture);
        			numberOfArea++;
        			maxSizeOfOneArea = Math.max(cnt, maxSizeOfOneArea);
        		}
        	}
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        
        return answer;
    }

	private int bfs(int row, int col, int m, int n, int val, int[][] picture) {
		int cnt = 1;
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(row, col));
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < m && 0 <= ny && ny < n) {
					if (!check[nx][ny] && picture[nx][ny] == val) {
						check[nx][ny] = true;
						q.add(new Point(nx, ny));
						cnt++;
					}
				}
			}
			
		}
		
		return cnt;
	}	
}
