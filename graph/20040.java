package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
	static int[] rank, parents;
	static int N, M;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input;
    	input = br.readLine().split(" ");
    	N = Integer.parseInt(input[0]);
    	M = Integer.parseInt(input[1]);
    	
    	make_set();
    	int answer = 0;
    	
    	for (int i = 0; i < M; i++) {
    		input = br.readLine().split(" ");
    		int x = Integer.parseInt(input[0]);
    		int y = Integer.parseInt(input[1]);
    		if (find(x) != find(y)) {
    			union(x, y);
    		} else {
    			answer = i + 1;
    			break;
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
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		rank = new int[N];
		for (int i = 1; i < N; i++) {
			rank[i] = 1;
		}
	}

}
