package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Pair implements Comparable<Pair>{
	int x, y, dist;
	
	public Pair(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}

	@Override
	public int compareTo(Pair o) {
		if (this.dist == o.dist) {
			return this.y - o.y;
		}
		return this.dist - o.dist;
	}
	
}

public class Main {
	static int N, M, D, answer, cnt;
	static int[][] map, copyMap;
	static String[] input;
	static boolean[] check;
	static int[] select;
	static List<Point> enemy;
	static PriorityQueue<Pair> pq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 세로
		M = Integer.parseInt(input[1]);		// 가로
		D = Integer.parseInt(input[2]);		// 궁수의 공격 거리 제한
		map = new int[N + 1][M + 1];		// 맵
		check = new boolean[M];				// 순열을 뽑기 위한 boolean
		select = new int[3];				// 뽑은 순열을 저장할 공간
		
		for (int row = 1; row <= N; row++) {
			input = br.readLine().split(" ");
			for (int col = 1; col <= M; col++) {
				map[row][col] = Integer.parseInt(input[col - 1]);
			}
		}
		// 궁수 자리배치를 위한 순열
		permutation(0, 0);
		
		System.out.println(answer);
	}

	private static void permutation(int index, int start) {
		if (index == 3) {
			copy();

			cnt = 0;

			while (check()) {
				// 적과 궁수의 거리 계산
				distanceCalc();
				// 남은 적들 밑으로 내리기	
				move();	
			}
			
			answer = Math.max(cnt, answer);
			
			return;
		}
		
		for (int i = start; i < M; i++) {
			if (!check[i]) {
				check[i] = true;
				select[index] = i + 1;
				permutation(index + 1, i + 1);
				check[i] = false;
			}
		}
		
	}
	
	// 밑으로 내려가는 작업
	private static void move() {
		for (int row = N; row >= 0; row--) {
			for (int col = 1; col <= M; col++) {
				if (row == N && copyMap[row][col] == 1) {
					copyMap[row][col] = 0;
				} else if (copyMap[row][col] == 1) {
					copyMap[row + 1][col] = copyMap[row][col];
					copyMap[row][col] = 0;
				}
 			}
		}
		
	}
	// 맵에 적이 있는지 체크
	private static boolean check() {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				if (copyMap[row][col] == 1) {
					return true;
				}
 			}
		}
		
		return false;
	}

	private static void copy() {
		copyMap = new int[N + 1][M + 1];
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				copyMap[row][col] = map[row][col];
 			}
		}
	}

	private static void distanceCalc() {
		enemy = new ArrayList<Point>();		// 적의 좌표 저장
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= M; col++) {
				if (copyMap[row][col] == 1) {
					enemy.add(new Point(row, col));
				}
 			}
		}
		
		// 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다
		for (int i = 0; i < 3; i++) {
			pq = new PriorityQueue<Pair>();
			int archerX = N + 1;
			int archerY = select[i];
			
			for (int j = 0; j < enemy.size(); j++) {
				Point point = enemy.get(j);
				int enemyX = point.x; 
				int enemyY = point.y;
				int dist = Math.abs(archerX - enemyX) + Math.abs(archerY - enemyY);
				// 궁수가 공격하는 적의 거리가 D이하인 경우
				if (dist <= D) {
					pq.add(new Pair(enemyX, enemyY, dist));
				}
				
			}
			
			if (!pq.isEmpty()) {
				Pair pair = pq.poll();
				int x = pair.x;
				int y = pair.y;
				if (copyMap[x][y] == 0)
					continue;
				// 맵에 적제거
				copyMap[x][y] = 0;
				// 적이 제거되었으므로 answer증가
				cnt++;

			}
			
		}
		
		
	}

}

