package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int R, C, T, answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static String[] input;
	static int[][] map, copyMap;
	static int[] airCleaner;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);			// 세로
		C = Integer.parseInt(input[1]);			// 가로
		T = Integer.parseInt(input[2]);			// 시간
		map = new int[R][C];
		airCleaner = new int[2];				// 공기청정기 위치
		
		int index = 0;
		for (int row = 0; row < R; row++) {
			input = br.readLine().split(" ");
			for (int col = 0; col < C; col++) {
				map[row][col] = Integer.parseInt(input[col]);
				// 로봇청소기 위치 저장
				if (map[row][col] == -1) {
					airCleaner[index++] = row;
				}
			}
		}
		
		while (T-- > 0) {
			// 로직 시작
			copyMap = new int[R][C];
			copyMap[airCleaner[0]][0] = -1;
			copyMap[airCleaner[1]][0] = -1;
			
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					// 미세먼지가 있을 경우
					if (map[row][col] > 0) {
						spread(row, col);
					} 
				}
			}
			
			upMove();
			downMove();
			
			copy();
			
		}
		
		sum();
		
		System.out.println(answer);
		
	}
	
	private static void sum() {
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (map[row][col] > 0) {
					answer += map[row][col];
				}
				
			}
		}
	}

	private static void copy() {
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				map[row][col] = copyMap[row][col];
			}
		}
	}

	// 아래쪽에 있는 공기청정기 기준으로 미세먼지 움직이기
	private static void downMove() {
		int airX = airCleaner[1];
		
		for (int row = airX + 1; row < R; row++) {
			if (copyMap[row - 1][0] == -1) {
				copyMap[row][0] = 0;
			} else {
				copyMap[row - 1][0] = copyMap[row][0];
			}
		}
		
		for (int col = 1; col < C; col++) {
			copyMap[R - 1][col - 1] = copyMap[R - 1][col];
		}
		
		for (int row = R - 2; row >= airX; row--) {
			copyMap[row + 1][C - 1] = copyMap[row][C - 1];
		}
		
		for (int col = C - 2; col >= 1; col--) {
			copyMap[airX][col + 1] = copyMap[airX][col];
		}
		
		copyMap[airX][1] = 0;
	
	}
	
	// 위쪽에 있는 공기청정기 기준으로 미세먼지 움직이기
	private static void upMove() {
		int airX = airCleaner[0];
		for (int row = airX - 2; row >= 0; row--) {
			// 공기청정기를 만나는 경우
			if (copyMap[row + 1][0] == -1) {
				copyMap[row][0] = 0;
			} else {
				copyMap[row + 1][0] = copyMap[row][0];
			}	
		}
		
		for (int col = 1; col < C; col++) {
			copyMap[0][col - 1] = copyMap[0][col];
		}
		
		for (int row = 1; row <= airX; row++) {
			copyMap[row - 1][C - 1] = copyMap[row][C - 1];
		}
		
		for (int col = C - 2; col > 0; col--) {
			copyMap[airX][col + 1] = copyMap[airX][col];
		}
		
		copyMap[airX][1] = 0;
		
	}

	// 미세먼지 뿌리기
	private static void spread(int x, int y) {
		int dust = map[x][y] / 5;
		int cnt = 0;
		
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			if (0 <= nx && nx < R && 0 <= ny && ny < C) {
				if (map[nx][ny] >= 0) {
					copyMap[nx][ny] += dust;
					cnt++;
				}
				
			}
		}
		
		copyMap[x][y] += map[x][y] - (map[x][y] / 5) * cnt;
		
	}


}

