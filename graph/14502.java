package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, M, answer;
	static int[][] map, copyMap;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Queue<Point> q;
	static String[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 세로 크기
		M = Integer.parseInt(input[1]);		// 가로 크기
		map = new int[N][M];				// 맵 생성
		// 입력값 넣기
		for (int row = 0; row < N; row++) {
			input = br.readLine().split(" ");
			for (int col = 0; col < M; col++) {
				map[row][col] = Integer.parseInt(input[col]);
			}
		}
		// 3개의 벽 세우는 모든 경우의 수
		combination(0, 0, 0);
		
		System.out.println(answer);
		
	}

	private static void combination(int x, int y, int cnt) {
		if (cnt == 3) {
			// 맵 복사
			copy();
			
			// 바이러스 퍼트리기
			virus();
			
			// 안전영역 개수 구하기
			count();
			
			return;
		}
		// 재귀를 통해 3개의 벽을 세우는 경우의 수 구하기
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {	
				if (map[row][col] == 0) {
					map[row][col] = 1;
					combination(row, col, cnt + 1);
					map[row][col] = 0;
				}
			}
		}
	}
	
	private static void copy() {
		copyMap = new int[N][M];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {	
				copyMap[row][col] = map[row][col];
			}
		
		}
		
	}
	// 안전영역 개수 구하기
	private static void count() {
		int cnt = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {	
				if (copyMap[row][col] == 0) {
					cnt++;
				}
			}
		}

		answer = Math.max(answer, cnt);
	}

	// 바이러스 퍼트리기
	private static void virus() {
		q = new LinkedList<Point>();

		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {	
				if (copyMap[row][col] == 2) {
					q.add(new Point(row, col));
					bfs();
				}
			}
		}
		
		
	}
	
	// 바이러스를 퍼트리기 위한 bfs
	private static void bfs() {
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (copyMap[nx][ny] == 0) {
						copyMap[nx][ny] = 2;
						q.add(new Point(nx, ny));
					}
				}
			}
			
		}
		
	}

}

