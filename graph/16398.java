package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Point {
	int x;
	int y;
	int cost;
	
	public Point(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}

public class Main {
	static int N;
	static List<Point> list;
	static int[] parents, rank;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());
    	int[][] map = new int[N][N];
    	list = new ArrayList<Point>();
    	
    	for (int row = 0; row < N; row++) {
    		String[] input = br.readLine().split(" ");
    		for (int col = 0; col < N; col++) {
    			map[row][col] = Integer.parseInt(input[col]);
    		}
    	}
    	
    	for (int row = 0; row < N - 1; row++) {
    		for (int col = row + 1; col < N; col++) {
    			list.add(new Point(row + 1, col + 1, map[row][col]));
    		}
    	}
    	Collections.sort(list, new Comparator<Point>() {
			@Override
			public int compare(Point o1, Point o2) {
				return o1.cost - o2.cost;
			}
		});
    	
    	make_set();
    	
    	long answer = 0;
    	for (int i = 0; i < list.size(); i++) {
    		int x = list.get(i).x;
    		int y = list.get(i).y;
    		int cost = list.get(i).cost;
    		
    		if (find(x) != find(y)) {
    			union(x, y);
    			answer += cost;
    		}
    	}
    	System.out.println(answer);
    	
	}

	private static void union(int x, int y) {
		int nx = find(x);
		int ny = find(y);
		
		if (rank[nx] < rank[ny]) {
			parents[nx] = ny;
		} else if (rank[nx] > rank[ny]) {
			parents[ny] = nx;
		} else {
			rank[nx]++;
			parents[ny] = nx;
		}
		
		
	}

	private static int find(int node) {
		if (parents[node] == node) {
			return node;
		}
		
		return find(parents[node]);
	}

	private static void make_set() {
		parents = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			parents[i] = i;
		}
		
		rank = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			rank[i] = 1;
		}
	}

}
