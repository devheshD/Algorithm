package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static String[] input;
	static int N, M, R, answer;
	static int[][] map;
	static boolean[][] check;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 행 개수
		M = Integer.parseInt(input[1]);		// 열 개수
		R = Integer.parseInt(input[2]);		// 라운드 횟수
		map = new int[N + 1][M + 1];				// 도미노 맵
		check = new boolean[N + 1][M + 1];
		
		// 도미노 정보 입력
		for (int row = 1; row <= N; row++) {
			input = br.readLine().split(" ");
			for (int col = 1; col <= M; col++) {
				map[row][col] = Integer.parseInt(input[col - 1]);
			}
		}
		
		for (int i = 0; i < R * 2; i++) {
			input = br.readLine().split(" ");
			// 공격 정보
			if (i % 2 == 0) {
				int attack_X = Integer.parseInt(input[0]);
				int attack_Y = Integer.parseInt(input[1]);
				int dir = 0;
				
				switch (input[2]) {
				case "W":
					dir = 1;
					break;
				case "S":
					dir = 2;
					break;
				case "N":
					dir = 3;
					break;
				};
				
				// 도미노가 이미 쓰러져 있는 곳을 공격할 경우
				if (check[attack_X][attack_Y]) continue;
						
				answer++;
				check[attack_X][attack_Y] = true;
				attack_domino(attack_X, attack_Y, dir, 1, map[attack_X][attack_Y]);
			
			}
			
			// 방어 정보
			else {
				int defense_X = Integer.parseInt(input[0]);
				int defense_Y = Integer.parseInt(input[1]);	
				
				// 넘어지지 않은 도미노를 세우려고 하는 경우
				if (!check[defense_X][defense_Y]) continue;
				check[defense_X][defense_Y] = false;
				
			}
			
		}
		
		// 결과 출력
		print();
		
	}

	private static void print() {
		System.out.println(answer);
		
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				// 도미노가 넘어진 것
				if (check[row][col]) {
					System.out.print("F ");
				} 
				// 도미노가 세워진 것
				else {
					System.out.print("S ");
				}
			}
			System.out.println();
		}
	}

	private static void attack_domino(int x, int y, int dir, int cnt, int height) {
		if (cnt == height) {
			return;
		}
		
		int nx = x + dx[dir];
		int ny = y + dy[dir];
		
		if (0 < nx && nx <= N && 0 < ny && ny <= M) {
			attack_domino(nx, ny, dir, cnt + 1, height);
			
			// 연쇄 작용이 일어나기 위한 재귀
			if (!check[nx][ny]) {
				attack_domino(nx, ny, dir, 1, map[nx][ny]);
			}

			// 도미노가 쓰러져 있지 않은 경우
			if (!check[nx][ny]) {
				check[nx][ny] = true;
				answer++;
				
			}
		}
		
	}


}

