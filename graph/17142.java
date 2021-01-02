package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
	static int N, M;
	static int answer = Integer.MAX_VALUE;
	static int[][] map, copyMap;
	static List<Pair> virus; 
	static boolean[] check;
	static Pair[] select;
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 연구소 크기
		M = Integer.parseInt(input[1]);		// 바이러스 개수
		map = new int[N][N];				// 연구소
		virus = new ArrayList<Pair>();
		
		for (int row = 0; row < N; row++) {
			input = br.readLine().split(" ");
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(input[col]);
				if (map[row][col] == 2) {
					virus.add(new Pair(row, col, 0));
				}
			}
		}
		
		select = new Pair[M];	// 선택된 바이러스
		check = new boolean[virus.size()];
		combination(0, 0);
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		
		System.out.println(answer);
	}

	private static void combination(int cnt, int start) {
		if (cnt == M) {
			// 바이러스 퍼트리기
			spread();
			
			// 바이러스 최소시간 찾기
			if (check()) {
				search();
			}
			
			return;
		}
		
		for (int index = start; index < virus.size(); index++) {
			if (!check[index]) {
				check[index] = true;
				select[cnt] = virus.get(index);
				combination(cnt + 1, index + 1);
				check[index] = false;
			}
		}
		
	}

	private static boolean check() {
		boolean flag = true;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (copyMap[row][col] == 0) {
					flag = false;
				}
				
			}
		}
		
		return flag;
	}

	private static void search() {
		int max = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (copyMap[row][col] >= max) {
					max = copyMap[row][col];
				}
			}
		}
		answer = Math.min(answer, max);
	}

	// 바이러스 퍼트리기
	private static void spread() {
		// 맵 복사본 생성
		copy();
		
		Queue<Pair> q = new LinkedList<Pair>();
		boolean[][] visit = new boolean[N][N];			// 방문 확인
		
		for (int i = 0; i < select.length; i++) {
			q.add(select[i]);
			visit[select[i].x][select[i].y] = true;
			
		}
		
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			int x = pair.x;
			int y = pair.y;
			int cnt = pair.cnt; 
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (!visit[nx][ny] && copyMap[nx][ny] == 0) {
						visit[nx][ny] = true;
						copyMap[nx][ny] = cnt + 1;
						q.add(new Pair(nx, ny, cnt + 1));
					} else if (!visit[nx][ny] && copyMap[nx][ny] == -3) {
						visit[nx][ny] = true;
						copyMap[nx][ny] = -2;
						q.add(new Pair(nx, ny, cnt + 1));
					}
				}
			}
			
		}
		
	}

	private static void copy() {
		copyMap = new int[N][N];
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				// 벽을 -1로 변환
				if (map[row][col] == 1) {
					copyMap[row][col] = -1;
				} else {
					copyMap[row][col] = map[row][col];
				}
			}
		}
		
		// 선택된 바이러스 치환
		for (int i = 0; i < select.length; i++) {
			copyMap[select[i].x][select[i].y] = -2;
		}
		
		// 선택되지 않은 바이러스
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (copyMap[row][col] == 2) {
					copyMap[row][col] = -3;
				}
			}
		}
		
	}

}

