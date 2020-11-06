package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
    static int N, answer;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1}; 

	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    int tc = Integer.parseInt(br.readLine());
	    char[] input;
	    for (int test_case = 1; test_case <= tc; test_case++) {
	        N = Integer.parseInt(br.readLine());
	        map = new char[N][N];
	        visit = new boolean[N][N];
	        // 지뢰 정보 입력
	        for (int row = 0; row < N; row++) {
	            input = br.readLine().toCharArray();
	            for (int col = 0; col < N; col++) {
	                map[row][col] = input[col]; 
	            }
	        }
	        
	
	        for (int row = 0; row < N; row++) {    
	            for (int col = 0; col < N; col++) {
	                boolean flag = false;
	                
	                if (map[row][col] == '.') {
	                    for (int i = 0; i < 8; i++) {
	                        int nx = row + dx[i];
	                        int ny = col + dy[i];
	                        
	                        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	                            if (map[nx][ny] == '*') {
	                                flag = true;
	                                break;
	                            }
	                        }
	                    }
	                    
	                    if (!flag && !visit[row][col]) {               
	                        visit[row][col] = true;
	                        dfs(row, col);
	                        answer++;
	                    }
	                }
	                
	
	            }
	        }
	
	        answer += check();
	       
	        bw.write("#" + test_case + " " + answer);
	        bw.newLine();
	        answer = 0;
	    }
	
	    bw.flush();
	    bw.close();
	
	}

	// .으로 되어있는 곳 갯수 세주기
	private static int check() {
	    int count = 0;
	    for (int row = 0; row < N; row++) {
	        for (int col = 0; col < N; col++) {
	            if (map[row][col] == '.') {
	                count++;
	            }
	
	        }
	    }
	    
	    return count;
	}


	private static void dfs(int x, int y) {
	    int cnt = 0;
	    int nx = 0;
	    int ny = 0;
	    // 8방향 탐색
	    for (int i = 0; i < 8; i++) {
	        nx = x + dx[i];
	        ny = y + dy[i];
	        
	        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	            // 주변에 지뢰가 있을 경우 cnt 증가
	            if (map[nx][ny] == '*') {
	                cnt += 1;
	            }
	            
	        }
	        
	    }
	    // 주변에 지뢰가 한개도 없어 카운트가 안된 경우
	    if (cnt == 0) {
	        map[x][y] = '0';
	        // 8 방향 탐색해 그 자리 이동
	        for (int i = 0; i < 8; i++) {
	            nx = x + dx[i];
	            ny = y + dy[i];
	           
	            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
	                if (!visit[nx][ny] && map[nx][ny] == '.') {
	                    visit[nx][ny] = true;
	                    dfs(nx, ny);
	                }
	            }
	        }
	        
	    } 
	    // 주변에 지뢰가 있어 카운트 된 경우
	    else {
	        map[x][y] = (char) (cnt + '0');
	    }
	    
	}
}