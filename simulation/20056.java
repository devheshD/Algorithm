package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

class FireBall {
	int m, s, d;
	
	public FireBall(int m, int s, int d) {
		this.m = m;
		this.s = s;
		this.d = d;
	}
	
}

public class Main {
	static String[] input;
	static int N, M, K, r, c, m, s, d, answer;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static List<FireBall>[][] map, copyMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 맵의 크기
		M = Integer.parseInt(input[1]);		// 파이어볼의 개수
		K = Integer.parseInt(input[2]);		// 마법사 상어 이동 횟수
		map = new LinkedList[N][N];			// 맵
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				map[row][col] = new LinkedList<FireBall>();
			}
		}
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			r = Integer.parseInt(input[0]) - 1;		// 위치 r행
			c = Integer.parseInt(input[1]) - 1;		// 위치 c열
			m = Integer.parseInt(input[2]);			// 질량
			s = Integer.parseInt(input[3]);			// 속력
			d = Integer.parseInt(input[4]);			// 방향
			
			map[r][c].add(new FireBall(m, s, d));
		
		}
		
		for (int i = 0; i < K; i++) {
			move();
		
			combine();
			
		}
		
		massCount();
		
		System.out.println(answer);
	}
	// 남아있는 질량들의 합 구하기
	private static void massCount() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				if (map[row][col].size() > 0) {
					for (int index = 0; index < map[row][col].size(); index++) {
						FireBall fireBall = map[row][col].get(index);
						answer += fireBall.m;
					}
				}
			}
		}
	}
	
	// 파이어볼 이동시키기
	private static void move() {
		// 카피 맵 생성
		copyMap = new LinkedList[N][N];		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				copyMap[row][col] = new LinkedList<FireBall>();
			}
		}
		
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				// 파이어 볼이 존재하는 경우
				if (map[row][col].size() > 0) {
					
					for (int index = map[row][col].size() - 1; index >= 0; index--) {
						FireBall fireBall = map[row][col].get(index);
						int mass = fireBall.m;
						int speed = fireBall.s;
						int dir = fireBall.d;
						int nRow = row + dx[dir] * speed % N;
						int nCol = col + dy[dir] * speed % N;
						
						if (nRow >= N) {
							nRow -= N;
						} else if (nRow < 0) {
							nRow += N;
						}
						
						if (nCol >= N) {
							nCol -= N;
						} else if (nCol < 0) {
							nCol += N;
						}
						
						copyMap[nRow][nCol].add(new FireBall(mass, speed, dir));
						map[row][col].remove(index);
					}
	
				}
			}
		}
	
		
	}

	// 파이어 볼 합치기
	private static void combine() {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				int mass = 0;
				int speed = 0;
				int evenCnt = 0;		// 짝수 카운트
				int oddCnt = 0;			// 홀수 카운트
				
				if (copyMap[row][col].size() > 1) {
					for (int index = 0; index < copyMap[row][col].size(); index++) {
						FireBall fireBall = copyMap[row][col].get(index);
						mass += fireBall.m;
						speed += fireBall.s;

						if (fireBall.d % 2 == 0) {
							evenCnt++;
						} else {
							oddCnt++;
						}
					}
					
					mass /= 5;
					speed /= copyMap[row][col].size();
					if (mass != 0) {
						if (evenCnt == copyMap[row][col].size() || oddCnt == copyMap[row][col].size()) {
							map[row][col].add(new FireBall(mass, speed, 0));
							map[row][col].add(new FireBall(mass, speed, 2));
							map[row][col].add(new FireBall(mass, speed, 4));
							map[row][col].add(new FireBall(mass, speed, 6));
						} else {
							map[row][col].add(new FireBall(mass, speed, 1));
							map[row][col].add(new FireBall(mass, speed, 3));
							map[row][col].add(new FireBall(mass, speed, 5));
							map[row][col].add(new FireBall(mass, speed, 7));
						}
					}
					
				} else if (copyMap[row][col].size() == 1) {
					FireBall fireBall = copyMap[row][col].get(0);
					map[row][col].add(new FireBall(fireBall.m, fireBall.s, fireBall.d));
				}
				
				
			}
		}
		
	}

}