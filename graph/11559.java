package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static final int R = 12;
	static final int C = 6;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int answer, cnt;
	static Queue<Point> q;
	static char[][] map;
	static boolean[][] visit;
	static boolean flag, check;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	map = new char[R][C];
    	q = new LinkedList<Point>();
    	// 뿌요뿌여 인풋 입력
    	for (int row = 0; row < R; row++) {
    		String input = br.readLine();
    		for (int col = 0; col < C; col++) {
    			map[row][col] = input.charAt(col);
    		}
    	}
    	check = true;
    	
    	while (check) {
    		check = false;
    		
    		for (int row = 0; row < R; row++) {
        		for (int col = 0; col < C; col++) {
        			if (map[row][col] != '.') {
        				visit = new boolean[R][C];
        				// dfs() => 뿌요뿌요가 4개 이상 모여 있는지 체크 
        				q.add(new Point(row, col));
        				cnt = 0;
        				bfs(row, col, map[row][col]);
        				// 뿌요뿌요가 4개 이상 모여있는 경우
        				if (flag) {
        					// 뿌요 없애기
        					delete();
        					flag = false;
        					check = true;
        				} 
        			
        			}
        		}
        	}
        	
        	// 뿌요뿌요를 밑으로 내려주기
        	move();
        	if (check) {
          		answer++;	
        	}
        	
    	}
    	
    	System.out.println(answer);
    	
	}

	private static void delete() {
		for (int row = 0; row < R; row++) {
    		for (int col = 0; col < C; col++) {
    			// 방문의 흔적이 있는 경우 .으로 변경
    			if (visit[row][col]) {
    				map[row][col] = '.';
    			}
    		}
    	}
		
	}

	private static void move() {
		int cnt = R - 1;
		// 최대 11번 내릴 수 있으므로
		while (cnt-- > 0) {
			for (int row = R - 2; row >= 0; row--) {
				for (int col = 0; col < C; col++) {
					// 현재 위치가 알파벳이고 밑에가 .인 경우 뿌요를 내리기
					if (map[row][col] != '.' && map[row + 1][col] == '.') {
						map[row + 1][col] = map[row][col];
						map[row][col] = '.';
					}
					
				}
			}
			
		}
	}

	private static void bfs(int row, int col, char color) {
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			
			if (cnt >= 4) {
				flag = true; 
			}
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if (0 <= nx && nx < R && 0 <= ny && ny < C) {
					if (!visit[nx][ny] && map[nx][ny] == color) {
						visit[nx][ny] = true;
						cnt += 1;
						q.add(new Point(nx, ny));
					}
				}
			}	
		}
		
	}

}
