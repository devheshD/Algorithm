package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Pair {
    int x;
    int y;
    int cnt;
    int jump;

    public Pair(int x, int y, int cnt, int jump) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.jump = jump;

    }
}

public class Main {
    static int K, W, H, answer;
    static int[][] map;
    static boolean[][][] visit;
    static int[] dx = {-1, 0, 1, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dy = {0, 1, 0, -1, -2, -1, 1, 2, 2, 1, -1, -2};
    static Queue<Pair> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        K = Integer.parseInt(br.readLine());
        input = br.readLine().split(" ");
        W = Integer.parseInt(input[0]);        // 가로 길이
        H = Integer.parseInt(input[1]);        // 세로 길
        map = new int[H][W];
        answer = Integer.MAX_VALUE;

        for (int row = 0; row < H; row++) {
            input = br.readLine().split(" ");
            for (int col = 0; col < W; col++) {
                map[row][col] = Integer.parseInt(input[col]);
            }
        }

        visit = new boolean[H][W][K + 1];
        q = new LinkedList<Pair>();
        q.add(new Pair(0, 0, 0, 0));
        visit[0][0][0] = true;

        bfs();

        System.out.println(answer);

    }

	private static void bfs() {
	    while (!q.isEmpty()) {
	        Pair pair = q.poll();
	        int x = pair.x;
	        int y = pair.y;
	        int cnt = pair.cnt;
	        int jump = pair.jump;
	        // 맨 오른쪽 아래에 도착했을 경우 리턴
	        if (x == H - 1 && y == W - 1) {
	            answer = Math.min(answer, cnt);
	            return;
	        }
	        // 4방향으로 갈경우 (원숭이처럼 이동할 경우)
	        for (int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            
	            if (0 <= nx && nx < H && 0 <= ny && ny < W) {
	                if (!visit[nx][ny][jump] && map[nx][ny] != 1) {
	                    visit[nx][ny][jump] = true;
	                    q.add(new Pair(nx, ny, cnt + 1, jump));
	                }
	            }
	        }
	        
	        if (jump < K) {
	            // 8방향으로 갈경우 (말처럼 이동할 경우)
	            for (int i = 4; i < 12; i++) {
	                int nx = x + dx[i];
	                int ny = y + dy[i];
	                
	                if (0 <= nx && nx < H && 0 <= ny && ny < W) {
	                    if (!visit[nx][ny][jump + 1] && map[nx][ny] != 1) {
	                        visit[nx][ny][jump + 1] = true;
	                        q.add(new Pair(nx, ny, cnt + 1, jump + 1));
	                    }
	                }
	            }
	        }
	        
	        
	    }
	    // 도착지점까지 도달 할 수 없는 경우
	    answer = -1;
	    
	}		// End of BFS();
}
