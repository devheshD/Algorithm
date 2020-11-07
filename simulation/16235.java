package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Tree implements Comparable<Tree> {
	int x, y, age;
	
	public Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}

	@Override
	public int compareTo(Tree o) {
		return this.age - o.age;
	}
	
}

public class Main {
	static int N, M, K;
	static int[][] nutrient, map;
	static PriorityQueue<Tree> q;
	static Queue<Tree> alive;
	static Queue<Tree> dead;
	static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input;
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 맵의 사이즈
		M = Integer.parseInt(input[1]);		// 상도가 심은 나무의 정보를 나타내는 수
		K = Integer.parseInt(input[2]);		// 연도
		
		// 겨울에 추가될 양분 정보 입력
		nutrient = new int[N + 1][N + 1];			
		for (int row = 1; row < N + 1; row++) {
			input = br.readLine().split(" ");
			for (int col = 1; col < N + 1; col++) {
				nutrient[row][col] = Integer.parseInt(input[col - 1]);
			}
		}
		
		// 심은 나무의 정보 입력
		q = new PriorityQueue<Tree>();
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int x = Integer.parseInt(input[0]);			// 심은 나무의 x좌표
			int y = Integer.parseInt(input[1]);			// 심은 나무의 y좌표
			int age = Integer.parseInt(input[2]);			// 심은 나무의 나이
			q.add(new Tree(x, y, age));
			
		}
		
		// 처음 양분의 정보 초기화
		map = new int[N + 1][N + 1];
		for (int row = 1; row < N + 1; row++) {
			for (int col = 1; col < N + 1; col++) {
				map[row][col] = 5;
			}
		}
		// 로직 시작
		alive = new LinkedList<Tree>();		// 살아 있는 나무 저장 
		dead = new LinkedList<Tree>();		// 죽은 나무 저장
		
		while (K-- > 0) {
			spring();
			
			summer();
			
			fall();
			
			winter();
				
		}
		
		int answer = q.size();

		System.out.println(answer);
	}
	
	private static void spring() {
		
		while (!q.isEmpty()) {
			Tree tree = q.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			// 봄에는 나무가 자신의 나이만큼 양분을 먹고, 나이가 1 증가한다. 
			if (age <= map[x][y]) {
				map[x][y] -= age; 
				age += 1;
				alive.add(new Tree(x, y, age));
			}
			// 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
			else {
				// 죽은 나무는 false로 변경
				dead.add(new Tree(x, y, age));
			}

			
		}
	
	}
	
	private static void summer() {
		// 여름에는 봄에 죽은 나무가 양분으로 변하게 된다.
		while (!dead.isEmpty()) {
			Tree tree = dead.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			// 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
			int addNutrient = age / 2;
			map[x][y] += addNutrient;  
		}
		
	}
	
	private static void fall() {
		// 가을에는 나무가 번식한다. 
		while (!alive.isEmpty()) {
			Tree tree = alive.poll();
			int x = tree.x;
			int y = tree.y;
			int age = tree.age;
			// 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
			if (age % 5 == 0) {
				// 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다. 
				for (int dir = 0; dir < 8; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					// 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
					if (1 <= nx && nx <= N && 1 <= ny && ny <= N) {
						q.add(new Tree(nx, ny, 1));
					}
					
				}
			} 
			
			q.add(new Tree(x, y, age));
			
		}
		
	}
	
	private static void winter() {
		// 겨울에는 S2D2가 땅을 돌아다니면서 땅에 양분을 추가한다. 
		// 각 칸에 추가되는 양분의 양은 A[r][c]이고, 입력으로 주어진다.
		for (int row = 1; row < N + 1; row++) {
			for (int col = 1; col < N + 1; col++) {
				map[row][col] += nutrient[row][col];
			}
		}
	
	}
	
}

