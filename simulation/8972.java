package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int R, C, robotX, robotY, answer;
	static char[][] map;
	static int[][] count;
	static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1};
	static int[] dy = {0, -1, 0, 1, -1, 0, 1, -1, 0, 1};
	static boolean flag;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputSize = br.readLine().split(" ");
		R = Integer.parseInt(inputSize[0]);			// 세로 크기
		C = Integer.parseInt(inputSize[1]);			// 가로 크기
		map = new char[R][C];
		count = new int[R][C];
		
		for (int row = 0; row < R; row++) {
			String input = br.readLine();
			for (int col = 0; col < C; col++) {
				map[row][col] = input.charAt(col);
				
				if (map[row][col] == 'I') {
					robotX = row;
					robotY = col;
					count[row][col] = -1;
				} else if (map[row][col] == 'R') {
					count[row][col] = 1;
				}
			}
		}
		
		
		String[] input = br.readLine().split("");
		
		for (int i = 0; i < input.length; i++) {
			answer = i + 1;
			int dir = Integer.parseInt(input[i]);
			robotX += dx[dir];
			robotY += dy[dir];
			
			if (0 <= robotX && robotX < R && 0 <= robotY && robotY < C) {
				// 종수의 로봇이 움직인 곳에 미친 아두이노가 있는 경우 종료
				if (map[robotX][robotY] == 'R') {
					flag = true;
					break;
				}
				// 종수의 로봇이 가만히 있지 않을 경우
				if (dir != 5) {
					map[robotX][robotY] = 'I';
					count[robotX][robotY] = -1;
					
					int tempX = robotX - dx[dir];
					int tempY = robotY - dy[dir];
					map[tempX][tempY] = '.';
					count[tempX][tempY] = 0;
					
				}
				// 탐색 시작
				for (int row = 0; row < R; row++) {
					for (int col = 0; col < C; col++) {
						if (map[row][col] == 'R') {
							minCalc(row, col);
						}
					}
				}
				
				remove();
				
				copy();
				
				if (flag) {
					break;
				}
				
			}
			
		}
		
		if (!flag) {
			for (int row = 0; row < R; row++) {
				for (int col = 0; col < C; col++) {
					System.out.print(map[row][col] + "");
				}
				System.out.println();
			}
		} else {
			System.out.println("kraj " + answer);
		}
		
		
	}

	// 카운트 값에 따라 아두이노 배치
	private static void copy() {
		map = new char[R][C];
		
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (count[row][col] == 1) {
					map[row][col] = 'R';
				} else if (count[row][col] == -1) {
					map[row][col] = 'I';
				} else {
					map[row][col] = '.';
				}
			}
		}
	}

	// 2개 또는 그 이상의 미친 아두이노가 같은 칸에 있는 경우에는 제거
	private static void remove() {
		for (int row = 0; row < R; row++) {
			for (int col = 0; col < C; col++) {
				if (count[row][col] > 1) {
					count[row][col] = 0;
				}
			}
		}
		
	}

	// 종수의 아두이노와 가까운 방향을 찾아 미친아두이노 이동
	private static void minCalc(int x, int y) {
		int dir = 0;
		int min = Integer.MAX_VALUE;
		
		for (int i = 1; i < 10; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
						
			if (0 <= nx && nx < R && 0 <= ny && ny < C) {
				int dis = Math.abs(nx - robotX) + Math.abs(ny - robotY);
				
				if (dis < min) {
					min = dis;
					dir = i;
				}
				
			}

		}
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if (map[nx][ny] == 'I') {
			flag = true;
			return;
		}
		
		count[x][y] -= 1;
		count[nx][ny] += 1;
		
	}

}