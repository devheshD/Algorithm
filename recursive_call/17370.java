package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static final int SIZE = 50;
	static int[] dx = {-1, -1, 1, 1, 1, -1};			
	static int[] dy = {0, 1, 1, 0, -1, -1};
	static boolean[][] visit;
	static int answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		visit = new boolean[SIZE][SIZE];
		visit[25][25] = true;
		dfs(25 + dx[0], 25 + dy[0], 0, 0, n);
		
		System.out.println(answer);
	}

	private static void dfs(int x, int y, int dir, int curCnt, int maxCnt) {
		if (curCnt == maxCnt) {
			if (visit[x][y]) {
				answer++;
			} 
			return;
		}

		if (curCnt < maxCnt && visit[x][y]) 
			return;
		
		int dir1 = (dir + 5) % 6;
		int dir2 = (dir + 1) % 6;
		
		visit[x][y] = true;
		dfs(x + dx[dir1], y + dy[dir1], dir1, curCnt + 1, maxCnt);
		dfs(x + dx[dir2], y + dy[dir2], dir2, curCnt + 1, maxCnt);
		visit[x][y] = false;
			
	}
	
}

