package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Computer { 
	int node1, node2, cost;
	
	public Computer(int node1, int node2, int cost) {
		this.node1 = node1;
		this.node2 = node2;	
		this.cost = cost;
	}
	
}

public class Main {
	static int N, M;
    static int[] parents, rank;
	static List<Computer> list;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	N = Integer.parseInt(br.readLine());	// 노드 개수
    	M = Integer.parseInt(br.readLine());	// 연결할 수 있는 선의 개수
    	list = new ArrayList<Computer>();
    	
    	for (int i = 0; i < M; i++) {
    		String[] input = br.readLine().split(" ");
    		int node1 = Integer.parseInt(input[0]);
    		int node2 = Integer.parseInt(input[1]);
    		int cost = Integer.parseInt(input[2]);	
    		list.add(new Computer(node1, node2, cost));
    	}
    	
    	Collections.sort(list, new Comparator<Computer>() {
			@Override
			public int compare(Computer o1, Computer o2) {
				return o1.cost - o2.cost;
			}
		});
    
    	make_set();
    	int answer = 0;
    	for (int i = 0; i < M; i++) {
    		int node1 = list.get(i).node1;
    		int node2 = list.get(i).node2;
    		// 사이클이 생기는지 안생기는지 확인
    		if (find(node1) != find(node2)) {
    			union(node1, node2);
    			answer += list.get(i).cost;
    		}
    		
    	}
    	
    	System.out.println(answer);
    	
    }

	private static void union(int node1, int node2) {
		int x = find(node1);
		int y = find(node2);
		
		if (rank[x] > rank[y]) {
			parents[y] = x;
		} else if (rank[x] < rank[y]) {
			parents[x] = y;
		} else {
			parents[x] = y;
			rank[y]++;		
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
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			rank[i] = 1;
		}
		
	}

}
