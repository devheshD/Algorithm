package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int x, y, cnt;
	
	public Point(int x, int y, int cnt) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
}

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static Queue<Point> q;
    static boolean[][] visit;
    static int N, M, R, C, L, answer;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        String[] input;
        
        for (int test_case = 1; test_case <= tc; test_case++) {
        	input = br.readLine().split(" ");
        	N = Integer.parseInt(input[0]); 		// 세로 크기
        	M = Integer.parseInt(input[1]);			// 가로 크기
        	R = Integer.parseInt(input[2]);			// 맨홀 뚜겅이 위치한 세로 위치
        	C = Integer.parseInt(input[3]);			// 맨홀 뚜껑이 위치한 가로 위치
        	L = Integer.parseInt(input[4]);			// 탈출 후 소요된 시간
        	map = new int[N][M];
        	
        	for (int row = 0; row < N; row++) {
        		input = br.readLine().split(" ");
        		for (int col = 0; col < M; col++) {
        			map[row][col] = Integer.parseInt(input[col]);
        		}
        	}
        	q = new LinkedList<Point>();
        	visit = new boolean[N][M];
        	
        	q.add(new Point(R, C, 1));
        	visit[R][C] = true;
        	
        	bfs();
        	
        	isCount();
        	
        	bw.write("#" + test_case + " " + answer);
            bw.newLine();
            q.clear();
            answer = 0;
           
        }

        bw.flush();
        bw.close();

    }

	private static void isCount() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (visit[row][col]) {
					answer++;
				}
			}
		}
	}

	private static void bfs() {
		while (!q.isEmpty()) {
			Point point = q.poll();
			int x = point.x;
			int y = point.y;
			int cnt = point.cnt;

			if (cnt == L) {
				return;
			}
		
			// 상 하 좌 우
			if (map[x][y] == 1) {
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, i)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					}
				}
			} 
			// 상 하
			else if (map[x][y] == 2) {
				int dir = 0;
				
				while (dir <= 2) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
						
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					
					}
					dir += 2;
				}
			} 
			// 좌 우
			else if (map[x][y] == 3) {
				int dir = 1;
				
				while (dir <= 3) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					
					}
					dir += 2;
				}
			} 
			// 상, 우
			else if (map[x][y] == 4) {
				int dir = 0;
				while (dir <= 1) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
						
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					
					}
					dir += 1;
				}
			} 
			// 하, 우
			else if (map[x][y] == 5) {
				int dir = 1;
				while (dir <= 2) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
						
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					
					}
					dir += 1;
				}
			} 
			// 하, 좌
			else if (map[x][y] == 6) {
				int dir = 2;
				while (dir <= 3) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
						
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
						
					
					}
					dir += 1;
				}
			} 
			// 상, 좌
			else if (map[x][y] == 7) {
				int dir = 3;
				int count = 1;
				while (count <= 2) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
						
					if (0 <= nx && nx < N && 0 <= ny && ny < M) {
						if (check(nx, ny, dir)) {
							if (!visit[nx][ny] && map[nx][ny] > 0) {
								visit[nx][ny] = true;
								q.add(new Point(nx, ny, cnt + 1));
							}
						}
					
					}
					dir = 0;
					count++;
				}
			}
			
		}
	}
	
	public static boolean check(int nx, int ny, int dir) {
		boolean flag = true;
		
		switch (dir) {
		case 0:
			if (map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 7) {
				flag = false;
			}
			break;
		case 1:
			if (map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 5) {
				flag = false;
			}
			break;
		case 2:
			if (map[nx][ny] == 3 || map[nx][ny] == 5 || map[nx][ny] == 6) {
				flag = false;
			}
			break;
		case 3:
			if (map[nx][ny] == 2 || map[nx][ny] == 6 || map[nx][ny] == 7) {
				flag = false;
			}
			break;

		}
		
		return flag;
	}
    
  
}

