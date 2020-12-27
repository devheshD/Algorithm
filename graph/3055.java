package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
	int x, y, cnt;
	
	public Pair(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Main {
	static int R, C;
	static int answer = Integer.MAX_VALUE;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static char[][] map;
	static boolean flag = true;
	static boolean[][] visit;
	static Queue<Pair> hedgehog;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputSize = br.readLine().split(" ");
		R = Integer.parseInt(inputSize[0]);		// 행
		C = Integer.parseInt(inputSize[1]);		// 열
		map = new char[R][C];
		visit = new boolean[R][C];
		hedgehog = new LinkedList<Pair>();
		
		for (int row = 0; row < R; row++) {
			String input = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = input.charAt(col);
				// 고슴도치 시작위치
				if (map[row][col] == 'S') {
					visit[row][col] = true;
					map[row][col] = '.';
					hedgehog.add(new Pair(row, col, 0));
				} 
			}
		}	
		
		int sec = 0;
		
		while (flag) {
			inundarse();
			sec++;
			bfs(sec);
			
		}

		if (answer == Integer.MAX_VALUE) {
			System.out.println("KAKTUS");
		} else {
			System.out.println(answer);
		}
	}

	private static void bfs(int sec) {
		while (!hedgehog.isEmpty()) {	
			Pair pair = hedgehog.peek();
			int x = pair.x;
			int y = pair.y; 
			int cnt = pair.cnt;
			// 매초가 지난경우
			if (sec == cnt) {
				return;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (0 <= nx && nx < R && 0 <= ny && ny < C) {
					if (map[nx][ny] == 'D') {
						answer = Math.min(answer, cnt + 1);
						flag = false;
						return;
					}
					
					if (!visit[nx][ny] && map[nx][ny] == '.') {			
						visit[nx][ny] = true;
						hedgehog.add(new Pair(nx, ny, cnt + 1));
					}
				}
			}	
			
			hedgehog.poll();
			
		}
		
		flag = false;
		
	}
	
	// 물 확장시키기
	private static void inundarse() {
		char[][] copyMap = new char[R][C];
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				copyMap[row][col] = map[row][col];
			}
		}

		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				// 물이 있는 경우
				if (copyMap[row][col] == '*') {
					// 4방향 물로 채우기
					for (int dir = 0; dir < 4; dir++) {
						int nx = row + dx[dir];
						int ny = col + dy[dir];
						
						if (0 <= nx && nx < R && 0 <= ny && ny < C) {
							if (copyMap[nx][ny] == '.') {
								map[nx][ny] = '*';
							}
						}
					}
				}
			}
		}
	
	}

}

