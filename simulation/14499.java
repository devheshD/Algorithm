package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};

	static int N, M, x, y, k;
	static int[][] map;
	static int[] dice;
	static List<Integer> answer;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input; 
    	input = br.readLine().split(" ");
    	N = Integer.parseInt(input[0]); 	// 세로 크기
    	M = Integer.parseInt(input[1]);		// 가로 크기
    	x = Integer.parseInt(input[2]);		// 주사위를 놓은 곳의 x좌표
    	y = Integer.parseInt(input[3]);		// 주사위를 놓은 곳의 y좌표
    	k = Integer.parseInt(input[4]);		// 명령의 개수
    	map = new int[N][M];
    	dice = new int[7];
    	
    	for (int row = 0; row < N; row++) {
    		input = br.readLine().split(" ");
    		for (int col = 0; col < M; col++) {
    			map[row][col] = Integer.parseInt(input[col]);
    		}
    	}
    	
    	answer = new ArrayList<Integer>();
    	input = br.readLine().split(" ");
    	int index = 0;
   
    	while (k-- > 0) {
    		// 입력 받은 방향 정보
    		int dir = Integer.parseInt(input[index++]);
    		// 다음 방향으로 이동할 좌표
    		int nx = x + dx[dir];
    		int ny = y + dy[dir];
    		
    		if (0 <= nx && nx < N && 0 <= ny && ny < M) {
    			// 주사위가 이동한 경로의 위치를 바꿔주기
        		change(dir);
        		
    			// 다음 방향으로 이동했을 때 맵에 쓰여있는 수가 0인 경우
    			if (map[nx][ny] == 0) {
    				map[nx][ny] = dice[6];
    				
    			} 
    			// 다음 방향으로 이동했을 때 맵에 쓰여있는 수가 0이 아닌 경우
    			else if (map[nx][ny] != 0) {
    				dice[6] = map[nx][ny];
    				map[nx][ny] = 0;
    			}
    			x = nx;
    			y = ny;
    			answer.add(dice[1]);
    		}
    	
    	
    	}	// End of while
    	
    	for (int i = 0; i < answer.size(); i++) {
    		System.out.println(answer.get(i));
    	}
    	
    	
	}

	private static void change(int dir) {
		int[] temp = dice.clone();
		
		switch (dir) {
		// 오른쪽
		case 1:
			dice[1] = temp[4];
			dice[3] = temp[1];
			dice[6] = temp[3];
			dice[4] = temp[6];
			break;
		// 왼쪽
		case 2:
			dice[6] = temp[4];
			dice[4] = temp[1];
			dice[1] = temp[3];
			dice[3] = temp[6];
			break;
		// 위
		case 3:
			dice[2] = temp[1];
			dice[6] = temp[2];
			dice[1] = temp[5];
			dice[5] = temp[6];
			break;
		// 아래
		case 4:
			dice[5] = temp[1];
			dice[1] = temp[2];
			dice[6] = temp[5];
			dice[2] = temp[6];
			break;

		}
		
		
	}
	
}
