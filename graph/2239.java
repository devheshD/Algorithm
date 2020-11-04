package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static final int size = 9;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visited;
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[size][size];
		visited = new boolean[size][size];
		for (int row = 0; row < size; row++) {
			String input = br.readLine();
			for (int col = 0; col < size; col++) {
				map[row][col] = input.charAt(col) - '0';
			}
		}
		
		start(0, 0);
		
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				System.out.print(map[row][col]);
			}
			System.out.println();
		}
	}
	
	public static void start(int x, int y) {
		if (x == 9 && y == 0) {
			flag = true;
			return;
		}
		// 현재 좌표가 0인경우
		if (map[x][y] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (rowCheck(x, i) && colCheck(y, i) && squareCheck(x, y, i)) {
					map[x][y] = i;
					// 행의 맨 끝열인 경우
					if (y == 8) {
						// 행 증가
						start(x + 1, 0);
					} else {
						// 열 증가
						start(x, y + 1);
					}
					if (flag)
						return;
				}
			}
			map[x][y] = 0;
			
		} 
		// 0이 아닌 경우
		else {
			// 행의 맨 끝열인 경우
			if (y == 8) {
				// 행 증가
				start(x + 1, 0);
			} else {
				// 열 증가
				start(x, y + 1);
			}
		}
		
	}
	
	// 가로 체크
	public static boolean rowCheck(int row, int num) {
		
		for (int i = 0; i < size; i++) {
			if (map[row][i] == num) {
				return false;
			}
		}
		
		return true;
	}
	
	// 세로 체크
	public static boolean colCheck(int col, int num) {
		for (int i = 0; i < size; i++) {
			if (map[i][col] == num) {
				return false;
			}
		}
		return true;
	}

	// 3X3체크
	public static boolean squareCheck(int row, int col, int num) {
		int nx = row / 3;
		int ny = col / 3;
		
		for (int x = nx * 3; x < nx * 3 + 3; x++) {
			for (int y = ny * 3; y < ny * 3 + 3; y++) {
				if (map[x][y] == num) {
					return false;
				}
			}
		}
		
		return true;
	}
}
