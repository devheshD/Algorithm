package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Pair {
	int r, c, s;
	
	public Pair(int r, int c, int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main {
	static int N, M, K;
	static int answer = Integer.MAX_VALUE;
	static String[] input;
	static int[][] map, copyMap, initMap;
	static List<Pair> list;
	static int[] select; 
	static boolean[] check;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 세로
		M = Integer.parseInt(input[1]);		// 가로
		K = Integer.parseInt(input[2]);		// 회전 연산 개수
		map = new int[N + 1][M + 1];
		initMap = new int[N + 1][M + 1];
		check = new boolean[K];
		select = new int[K];
		list = new ArrayList<Pair>();
		
		for (int row = 1; row <= N; row++) {
			input = br.readLine().split(" ");
			for (int col = 1; col <= M; col++) {
				map[row][col] = Integer.parseInt(input[col - 1]);
				initMap[row][col] = map[row][col];
			}
		}
		// 순열 뽑기 위해 리스트에 값 저장
		while (K-- > 0) {
			input = br.readLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int s = Integer.parseInt(input[2]);
			list.add(new Pair(r, c, s));
		}
		// 순열 시작
		permutation(0);
		
		System.out.println(answer);
		
	}
	
	
	private static void permutation(int index) {
		if (index == list.size()) {
			// 초기맵 다시 만들기
			init();
			// 회전시키기 위한 준비 메서드
			rotate();
			// 각 행의 값 중 최소값 구하기
			count();
			
			return;
		}
		
		// 순열을 뽑기 위한 로직
		for (int i = 0; i < list.size(); i++) {
			if (!check[i]) {
				check[i] = true;
				select[index] = i;
				permutation(index + 1);
				check[i] = false;
			}
		}
	}


	private static void count() {
		for (int row = 1; row <= N; row++) {
			int cnt = 0;
			for (int col = 1; col <= M; col++) {
				cnt += map[row][col];
			}
			answer = Math.min(answer, cnt);
		}
		
	}


	private static void rotate() {
		for (int i = 0; i < select.length; i++) {
			// 맵 복사본 만들기
			copy();
			
			Pair pair = list.get(select[i]);
			int r = pair.r;
			int c = pair.c;
			int s = pair.s;
			
			for (int j = 1; j <= s; j++) {
				calc(r - j, c - j, j * 2);
			}
			
		}
		
	}


	private static void init() {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				map[row][col] = initMap[row][col];
				
			}
		}
	}


	private static void copy() {
		copyMap = new int[N + 1][M + 1];
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				copyMap[row][col] = map[row][col];
			}
		}
		
	}


	private static void calc(int r, int c, int len) {
		// 왼쪽 변 위에서 위로 옮기기
		int row = r + 1;
		for (int i = 0; i < len; i++) {
			map[row - 1][c] = copyMap[row][c];
			row++;
		}

		// 위쪽 변 왼쪽에서 오른쪽으로 옮기기
		int col = c;
		for (int i = 0; i < len; i++) {
			map[r][col + 1] = copyMap[r][col];
			col++;
		}

		// 오른쪽 변 위에서 아래로 옮기기
		row = r;
		for (int i = 0; i < len; i++) {
			map[row + 1][c + len] = copyMap[row][c + len];
			row++;
		}

		// 아래쪽 변 오른쪽에서 왼쪽으로 옮기기
		col = c + len;
		for (int i = 0; i < len; i++) {
			map[r + len][col - 1] = copyMap[r + len][col];
			col--;
		}
		
	}

}

