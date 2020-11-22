package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M, answer;
	static int[][] map;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputSize = br.readLine().split(" ");
		N = Integer.parseInt(inputSize[0]);
		M = Integer.parseInt(inputSize[1]);
		map = new int[N][M];
		
		for (int row = 0; row < N; row++) {
			String[] input = br.readLine().split(" ");
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(input[col]);
			}
		}
		
		while (check()) {
			// 실내 공기 영역 분리
			air();
			
			// 공기와 접촉하는 곳 찾기
			contact();
			
			// 공기와 접촉한 곳 제거
			delete();
		}
		
		System.out.println(answer);
		
	}

	private static boolean check() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] == 1) {
					return true;
				}
			}
		}
		
		return false;
	}

	private static void delete() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				// 공기와 2번이상 접촉했었던 곳 제거
				if (map[row][col] == 2) {
					map[row][col] = 0;
				} 
				// 실내공기 영역 다시 초기화
				else if (map[row][col] == -1) {
					map[row][col] = 0;
				}
			}
		}
		
		answer++;
	}

	private static void contact() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				// 치즈 영역일 경우
				if (map[row][col] == 1) {
					int cnt = 0;
					
					for (int i = 0; i < 4; i++) {
						int nx = row + dx[i];
						int ny = col + dy[i];
						
						if (0 <= nx && nx < N && 0 <= ny && ny < M) {
							// 공기와 맞닿는 경우
							if (map[nx][ny] == -1) {
								cnt++;
							}
						}
					}
					// 공기와 맞닿는 곳이 2곳 이상일 경우
					if (cnt >= 2) {
						map[row][col] = 2;
					}
				}
				
			}
		}
	}

	private static void air() {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(0, 0));
		map[0][0] = -1;
		
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = -1;
						q.add(new Point(nx, ny));
					}
				}
			}
			
		}
		
	}

}