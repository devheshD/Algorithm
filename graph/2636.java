package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int W, H, answer_time, answer_piece;
	static int[][] map;
	static Queue<Point> q;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input;
    	input = br.readLine().split(" ");
    	H = Integer.parseInt(input[0]);
    	W = Integer.parseInt(input[1]);
    	map = new int[H][W];
    	q = new LinkedList<Point>();
    	
    	for (int row = 0; row < H; row++) {
    		input = br.readLine().split(" ");
    		for (int col = 0; col < W; col++) {
    			map[row][col] = Integer.parseInt(input[col]);
    		}
    	}
    	
    	while (search()) {
    		q.add(new Point(0, 0));
        	map[0][0] = -1;
        	// 바깥 영역 -1로 칠하기
        	// 가운데 뚫려있는곳과 구분하기 위해서
        	outside_bfs();
        	
        	q.add(new Point(0, 0));
        	// 치즈 겉부분 녹이기
        	melt_bfs();
        	answer_time++;
    	}
    	
    	System.out.println(answer_time);
    	System.out.println(answer_piece);
    	
	}

	private static boolean search() {
		for (int row = 0; row < H; row++) {
    		for (int col = 0; col < W; col++) {
    			if (map[row][col] != 0) {
    				return true;
    			}
    		}
    	}
		
		return false;
	}

	private static void melt_bfs() {
		int cnt = 0;
		visit = new boolean[H][W];
		visit[0][0] = true;
		
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < H && 0 <= ny && ny < W) {
					if (!visit[nx][ny] && map[nx][ny] == 1) {
						visit[nx][ny] = true;
						map[nx][ny] = 0;
						cnt++;
					} else if (!visit[nx][ny] && map[nx][ny] == -1) {
						visit[nx][ny] = true;
						q.add(new Point(nx, ny));
					}
						
				}
			}
			
		}
		
		answer_piece = cnt;
		
		// -1인 구간 복구
		restore();
		
	}

	private static void restore() {
		for (int row = 0; row < H; row++) {
    		for (int col = 0; col < W; col++) {
    			if (map[row][col] == -1) {
    				map[row][col] = 0;
    			} 
    		
    		}
    	}
		
	}

	private static void outside_bfs() {
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < H && 0 <= ny && ny < W) {
					if (map[nx][ny] == 0) {
						map[nx][ny] = -1;
						q.add(new Point(nx, ny));
					}
						
				}
			}
			
		}
	}

}
