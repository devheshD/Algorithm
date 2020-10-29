package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Pair {
	int x, y, dir;
	
	public Pair(int x, int y, int dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
}

public class Main {
	static int N, K, L, answer;
	static Deque<Pair> dq;
	static int[][] map;
	static boolean[][] visit;
	static String[][] info;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());	// 보드의 크기
    	K = Integer.parseInt(br.readLine());	// 사과의 개수
    	dq = new ArrayDeque<Pair>();
    	map = new int[N][N];
    	visit = new boolean[N][N];

    	for (int i = 0; i < K; i++) {
    		String[] input = br.readLine().split(" ");
    		int row = Integer.parseInt(input[0]) - 1;
    		int col = Integer.parseInt(input[1]) - 1;
    		// 사과의 위치 저장
    		map[row][col] = -1;
    	}
    	
    	L = Integer.parseInt(br.readLine()); // 방향 전환 정보 횟수
    	info = new String[L][2];
    	// 방향 전환 정보 입력
    	for (int i = 0; i < L; i++) {
    		String[] input = br.readLine().split(" ");
    		info[i][0] = input[0];
    		info[i][1] = input[1];
    	}
    	
    	visit[0][0] = true;
    	dq.addLast(new Pair(0, 0, 1));
    	
    	bfs();
    	
	}

	private static void bfs() {
		int index = 0;
		
		while (true) {
			answer++;
			Pair pair = dq.peek();
			int dir = pair.dir;
			int nx = pair.x + dx[dir];
			int ny = pair.y + dy[dir];
			
			if (end_game(nx, ny)) {
				System.out.println(answer);
				return;
			}
			
			// 시간이 되어 방향이 바뀌는 경우
			if (index < L && answer == Integer.parseInt(info[index][0])) {
				char ch = info[index][1].charAt(0);

				switch (ch) {
				case 'D':
					dir += 1;
					if (dir == 4) {
						dir = 0;
					}
					break;
				case 'L':
					dir -= 1;
					if (dir == -1) {
						dir = 3;
					}
					break;
				}
				
				index++;
			}
			
			// 사과가 없는 경우
			if (map[nx][ny] == 0) {
				visit[nx][ny] = true;
				dq.addFirst(new Pair(nx, ny, dir));
				Pair p =  dq.pollLast();
				int tail_x = p.x;
				int tail_y = p.y;
				visit[tail_x][tail_y] = false;
			}
			// 사과가 있는 경우
			else if (map[nx][ny] == -1) {
				visit[nx][ny] = true;
				dq.addFirst(new Pair(nx, ny, dir));
				map[nx][ny] = 0;
			}
			
			
		}
		
	}

	private static boolean end_game(int nx, int ny) {
		// 뱀이 범위를 벗어날 경우
		if (nx < 0 || ny < 0 || nx >= N || ny >= N)
			return true;
		if (visit[nx][ny])
			return true;
		return false;
	}

}
