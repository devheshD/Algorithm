package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static double[] percent;
	static boolean[][] visit;
	static int N;
	static double answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);			// 취할 행동 횟수
		percent = new double[4];
		for (int i = 0; i < 4; i++) {
			percent[i] = Double.parseDouble(input[i + 1]) * 0.01;
		}
		// 가운데를 기준으로 dfs탐색 시작
		visit = new boolean[30][30];
		visit[15][15] = true;
		dfs(15, 15, 0, 1);
		
		System.out.println(answer);
	}

	private static void dfs(int x, int y, int cnt, double res) {
		// N번의 행동을 취했을 경우
		if (cnt == N) {
			answer += res;
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0 <= nx && nx < 30 && 0 <= ny && ny < 30) {
				if (!visit[nx][ny]) {
					visit[nx][ny] = true;
					dfs(nx, ny, cnt + 1, res * percent[i]);
					visit[nx][ny] = false;
				}
			}
		}
		
		
	}

	
}

