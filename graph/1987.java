package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int R, C, answer;
	static char[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[] alphabetCheck;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] sizeInput = br.readLine().split(" ");
    	R = Integer.parseInt(sizeInput[0]);
    	C = Integer.parseInt(sizeInput[1]);
    	map = new char[R][C];
    	alphabetCheck = new boolean[26];
    	// 맵 입력
    	for (int row = 0; row < R; row++) {
    		String input = br.readLine();
    		for (int col = 0; col < C; col++) {
    			map[row][col] = input.charAt(col);
    		}
    	}
		
		// 현재 위치 방문 체크
		alphabetCheck[map[0][0] - 'A'] = true;
		
    	dfs(0, 0, 1);	
    	
    	System.out.println(answer);
    	
	}

	private static void dfs(int x, int y, int cnt) {
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (check(nx, ny)) {
				// 중복되는 알파벳을 만난 경우
				if (alphabetCheck[map[nx][ny] - 'A']) {
					answer = Math.max(cnt, answer);
				} else {
					alphabetCheck[map[nx][ny] - 'A'] = true;	
					dfs(nx, ny, cnt + 1);
					alphabetCheck[map[nx][ny] - 'A'] = false;
				}
			}
			
		}
		
	}
	
	// 맵의 범위 체크
	private static boolean check(int x, int y) {
		if (0 <= x && x < R && 0 <= y && y < C) {
			return true;
		}
		
		return false;
	}

}
