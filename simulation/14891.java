package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] wheel;
	static int K, answer;
	static int[] rotateCheck;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		wheel = new int[5][9];
		
		for (int i = 1; i <= 4; i++) {
			input = br.readLine().split("");
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = Integer.parseInt(input[j]);
			}
		}
		// 회전 횟수
		K = Integer.parseInt(br.readLine());		
		// 회전 시킬 획수
		for (int i = 0; i < K; i++) {
			input = br.readLine().split(" ");
			int wheelNumber = Integer.parseInt(input[0]);		// 회전시킬 바퀴 번호
			int direction = Integer.parseInt(input[1]);			// 방향 (1:시계, -1:반시계)
			
			rotateCheck = new int[5];
			rotateCheck[wheelNumber] = direction;
			rightCheck(wheelNumber + 1, direction * -1);
			leftCheck(wheelNumber - 1, direction * -1);
			// 톱니바퀴 회전 시키기
			rotate();
		}
		
		calc();
		
		System.out.println(answer);
	}
	
	private static void calc() {
		int point = 1;
		
		for (int i = 1; i <= 4; i++) {
			if (wheel[i][0] == 1) {
				answer += point;	
			}	
			point *= 2;
		}
		
	}

	private static void rotate() {
		
		for (int i = 1; i <= 4; i++) {
			// 반시계 방향으로 회전해야되는경우
			if (rotateCheck[i] == -1) {
				int first = wheel[i][0];
				for (int index = 1; index < 8; index++) {
					wheel[i][index - 1] = wheel[i][index];
				}
				wheel[i][7] = first;
			} 
			// 시계 방향으로 회전해야 되는 경우
			else if (rotateCheck[i] == 1) {
				int last = wheel[i][7];
				for (int index = 6; index >= 0; index--) {
					wheel[i][index + 1] = wheel[i][index];
				}
				wheel[i][0] = last;
			}
			
		}
		
	}

	// 오른쪽 방향 바꿀 수 있는 것들 체크
	private static void rightCheck(int wheelNumber, int direction) {
		if (wheelNumber == 5) {
			return;
		}
		
		if (wheel[wheelNumber - 1][2] != wheel[wheelNumber][6]) {
			rotateCheck[wheelNumber] = direction;
			rightCheck(wheelNumber + 1, direction * -1);
		}
	
	}
	
	
	// 왼쪽 방향 바꿀 수 있는 것들 체크
	private static void leftCheck(int wheelNumber, int direction) {
		if (wheelNumber == 0) {
			return;
		}
		
		if (wheel[wheelNumber][2] != wheel[wheelNumber + 1][6]) {
			rotateCheck[wheelNumber] = direction;
			leftCheck(wheelNumber - 1, direction * -1);
		}
	
	}
	
}

