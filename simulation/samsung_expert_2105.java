package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int N, answer;
    static int[][] map;
    static boolean[] numCheck;
    static int[] dx = {1, 1, -1, -1};
    static int[] dy = {1, -1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        String[] input;

        for (int test_case = 1; test_case <= tc; test_case++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            answer = -1;
            // 입력값 받기
            for (int row = 0; row < N; row++) {
                input = br.readLine().split(" ");
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(input[col]);
                }
            }

            for (int row = 0; row < N - 2; row++) {
                for (int col = 1; col < N - 1; col++) {
                    numCheck = new boolean[101];
                    // 시작지점의 숫자 방문 표시
                    dfs(row, col, row, col, 0, 0);
                }
            }

            bw.write("#" + test_case + " " + answer);
            bw.newLine();

        }

        bw.flush();
        bw.close();

    }

	private static void dfs(int x, int y, int finishX, int finishY, int dir, int cnt) {
	    // 우상 방향에 시작지점에 도달했을 경우
	    if (x == finishX && y == finishY && dir == 3) {
	        answer = Math.max(cnt, answer);
	        return;
	    }
	    
	    // 방향 대로 탐색 시작
	    for (int i = dir; i < 4; i++) {
	        // 여기가 중요!!! 바로 다음 방향까지만 가야되기 때문에
	        if (i <= dir + 1) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	
	            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	                if (!numCheck[map[nx][ny]]) {
	                    numCheck[map[nx][ny]] = true;
	                    dfs(nx, ny, finishX, finishY, i, cnt + 1);
	                    numCheck[map[nx][ny]] = false;    
	                }
	            }
	        }
	        
	        
	    }
	    
	    
	}
}