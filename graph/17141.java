package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Pair {
	int x, y, cnt;
	
	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Pair(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	
}


public class Main {
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int answer = Integer.MAX_VALUE;
	static int[][] map, copyMap;
	static boolean[][] check;
	static List<Pair> virusCoordinate;
	static boolean[] virusCheck;
	static Pair[] selectVirus;
	static Queue<Pair> q;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);			// 연구소 크기
		M = Integer.parseInt(input[1]);			// 바이러스 개수
		map = new int[N][N];
		copyMap = new int[N][N];
		virusCoordinate = new ArrayList<Pair>();
		// 연구소 상태 입력
		for (int row = 0; row < N; row++) {
			input = br.readLine().split(" ");
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(input[col]);
				// 바이러스를 놓을 수 있는 자리일 경우
				if (map[row][col] == 2) {
					virusCoordinate.add(new Pair(row, col));
				}
			}
		}
		
		virusCheck = new boolean[virusCoordinate.size()];			// 조합하기 위한 바이러스 체크 좌표
		selectVirus = new Pair[M];							// 선택된 바이러스 위치 좌표
		// 조합 시작
		combination(0, 0);
		
		answer = answer == Integer.MAX_VALUE ? -1 : answer;
		
		System.out.println(answer);
	}

	private static void combination(int cnt, int start) {
		if (cnt == M) {
			copy();

			spread();
			
			return;
		}
		
		for (int index = start; index < virusCoordinate.size(); index++) {
			if (!virusCheck[index]) {
				virusCheck[index] = true;
				selectVirus[cnt] = virusCoordinate.get(index);
				combination(cnt + 1, index + 1);
				virusCheck[index] = false;
			}
		}
	}
	// 바이러스 퍼트리기
	private static void spread() {
		int maxCnt = 0;
		
		while (!q.isEmpty()) {
			Pair pair = q.poll();
			int x = pair.x;
			int y = pair.y;
			int cnt = pair.cnt;
			
			maxCnt = Math.max(cnt, maxCnt);
			
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (!check[nx][ny] && copyMap[nx][ny] == 0) {
						check[nx][ny] = true;
						copyMap[nx][ny] = cnt + 1;
						q.add(new Pair(nx, ny, cnt + 1));
					}
				} 
			}
		}

		// 빈칸이 있는지 체크
		if (blank()) {
			answer = Math.min(answer, maxCnt);
		}
		
	}


	private static boolean blank() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (copyMap[row][col] == 0) {
					return false;
				}
			}
		}
		
		return true;
	}

	// 복사맵 정보 저장
	private static void copy() {
		copyMap = new int[N][N];
		check = new boolean[N][N];
		q = new LinkedList<Pair>();
		
		// 선택된 바이러스 좌표 copyMap에 -1로 저장
		for (int i = 0; i < M; i++) {
			int x = selectVirus[i].x;
			int y = selectVirus[i].y;
			copyMap[x][y] = -1;
			check[x][y] = true;
			q.add(new Pair(x, y, 0));
		}
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				// 기존 맵이 벽일경우
				if (map[row][col] == 1) {
					copyMap[row][col] = 1;
				}
				// 기존 맵이 바이러스를 뿌릴수 있는 위치이지만 아직 선택되지 않은 자리인 경우
				else if (map[row][col] == 2 && copyMap[row][col] != -1) {
					copyMap[row][col] = 0;
				}
				
			}
		}
		
	}

}

