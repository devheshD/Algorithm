package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int n, r;
	static boolean flag;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		int tc = Integer.parseInt(br.readLine());
		for (int test_case = 0; test_case < tc; test_case++) {
			input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);				// 보드의 크기
			r = Integer.parseInt(input[1]);				// 우향우 거울의 개수
			map = new int[n + 2][n + 2];				// 보드
			visited = new boolean[n + 2][n + 2][4];		// 사이클 생기는지 여부 체크
			for (int i = 0; i < r; i++) {
				input = br.readLine().split(" ");
				int x = Integer.parseInt(input[0]);		// 우향우 거울이 배치된 좌표 x
				int y = Integer.parseInt(input[1]);		// 우향우 거울이 배치된 좌표 y
				map[x][y] = 1;
			}
			
			input = br.readLine().split(" ");
			int startX = Integer.parseInt(input[0]);		// 레이저 좌표 x
			int startY = Integer.parseInt(input[1]);		// 레이저 좌표 y
			// 처음 시작될 방향 정해주기
			int dir = 0;
			if (startX == n + 1) {
				dir = 0;
			} else if (startX == 0) {
				dir = 2;
			} else if (startY == n + 1) { 
				dir = 3;
			} else {
				dir = 1;
			}
			
			flag = true;
			visited[startX][startY][dir] = true;
			dfs(startX, startY, dir);
			
			if (flag) {
				System.out.println("0 0");
			}
			
		}
		
	}

	private static void dfs(int x, int y, int dir) {
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if (nx == 0 || ny == 0 || nx == n + 1 || ny == n + 1) {
			flag = false;
			System.out.println(nx + " " + ny);
			return;
		}
		
		
		// 레이저를 만난 경우 오른쪽 90도로 변경
		if (map[nx][ny] == 1) {
			dir = (dir + 1) % 4;
		}
		
		if (!visited[nx][ny][dir]) {
			visited[nx][ny][dir] = true;
			dfs(nx, ny, dir);
		}
		
		
	}

}