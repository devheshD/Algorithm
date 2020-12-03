package com.baekjoon.java;

import java.util.Scanner;

public class Main {
	final static int SIZE = 5;
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int[][] map = new int[SIZE][SIZE];
		boolean[][] check = new boolean[SIZE][SIZE];
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				map[row][col] = scan.nextInt();
			}
		}
		
		boolean flag = false;
		int answer = 0;
		
		for (int i = 1; i <= 25; i++) {
			int num = scan.nextInt();
			// 불린 숫자 빙고판에 체크
			remove(map, check, num);
			answer = i;
			// 15개 이상 불릴 경우 매번 빙고판 체크
			if (i >= 12) {
				flag = bingoCheck(check);
			}
			// 3개이상의 빙고 나오면 탈출
			if (flag)
				break;
			
		}
		
		System.out.println(answer);
		
	}

	private static boolean bingoCheck(boolean[][] check) {
		int cnt = 0;
		// 가로 검사
		for (int row = 0; row < SIZE; row++) {
			boolean flag = true;
			for (int col = 0; col < SIZE; col++) {
				if (!check[row][col]) {
					flag = false;
				}	
			}
			// 가로가 모두 true인 경우
			if (flag)
				cnt++;
			
		}
		// 세로 검사
		for (int col = 0; col < SIZE; col++) {
			boolean flag = true;
			for (int row = 0; row < SIZE; row++) {
				if (!check[row][col]) {
					flag = false;
				}	
			}
			// 세로가 모두 true인 경우
			if (flag)
				cnt++;
			
		}
		// 왼쪽위에서 오른쪽 아래 대각선 검사
		boolean flag = true;
		for (int index = 0; index < SIZE; index++) {
			if (!check[index][index]) {
				flag = false;
				break;
			}
		}
		if (flag) {
			cnt++;
		}
		
		// 오른쪽위에서 왼쪽 아래 대각선 검사
		flag = true;
		for (int index = 0; index < SIZE; index++) {
			if (!check[index][4 - index]) {
				flag = false;
				break;
			}
		}
		if (flag) {
			cnt++;
		}
		
		// cnt가 3개 이상인지 확인
		flag = false;
		if (cnt >= 3) {
			flag = true;
		}
		
		return flag;
	}

	private static void remove(int[][] map, boolean[][] check, int num) {
		loop:
		for (int row = 0; row < SIZE; row++) {
			for (int col = 0; col < SIZE; col++) {
				if (map[row][col] == num) {
					check[row][col] = true;
					break loop;
				}
			}
		}
	}
	
}

