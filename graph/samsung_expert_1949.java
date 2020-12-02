package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int N, K, answer;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        String[] input;
        for (int test_case = 1; test_case <= tc; test_case++) {
            input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            K = Integer.parseInt(input[1]);
            map = new int[N][N];
            visited = new boolean[N][N];
            // 입력 받기
            int maxHeight = 0;
            for (int row = 0; row < N; row++) {
                input = br.readLine().split(" ");
                for (int col = 0; col < N; col++) {
                    map[row][col] = Integer.parseInt(input[col]);
                    maxHeight = Math.max(map[row][col], maxHeight);
                }
            }

            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (map[row][col] == maxHeight) {
                        visited[row][col] = true;
                        dfs(row, col, true, 1, maxHeight);
                        visited[row][col] = false;
                    }
                }
            }

            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            answer = 0;

        }

        bw.flush();
        bw.close();

    }

	private static void dfs(int x, int y, boolean flag, int cnt, int curHeight) {
			// 현재 몇번왔는지가 최대값이라면 기억
	        answer = Math.max(cnt, answer);
	        // 4방향에 대한 다음칸에 대해서
	        for (int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            // 지도밖으로 나가는것은 패스
	            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	            	// 현재높이보다 다음칸의 높이가 작더면
	                if (!visited[nx][ny] && curHeight > map[nx][ny]) {
	                    visited[nx][ny] = true;
	                    dfs(nx, ny, flag, cnt + 1, map[nx][ny]);
	                    visited[nx][ny] = false;
	                } 
	                // 현재높이보다 다음칸의 높이가 작지는 않지만  k만큼 깠을때 갈 수 있다면
	                // 해당위치의 높이를 현재값의 -1로 전달
	                else if (flag && !visited[nx][ny] && curHeight > map[nx][ny] - K) {
	                    visited[nx][ny] = true;
	                    dfs(nx, ny, false, cnt + 1, curHeight - 1);
	                    visited[nx][ny] = false;
	                }
	
	            }
	
	        }
	        
	
	}
}

