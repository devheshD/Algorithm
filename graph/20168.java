package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node {
	int end, cost;
	
	public Node(int end, int cost) {
		this.end = end;
		this.cost = cost;
	}
	
}

public class Main {
	static String[] input;
	static int N, M, A, B, C;
	static int answer = Integer.MAX_VALUE;
	static boolean[] visit;
	static List<Node>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);		// 교차로 개수
		M = Integer.parseInt(input[1]);		// 골목 개수
		A = Integer.parseInt(input[2]);		// 시작 교차로 번호
		B = Integer.parseInt(input[3]);		// 도착 교차로 번호
		C = Integer.parseInt(input[4]);		// 가진돈 
		// 인스턴스 생성
		list = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			input = br.readLine().split(" ");
			int start = Integer.parseInt(input[0]);
			int end = Integer.parseInt(input[1]);
			int money = Integer.parseInt(input[2]);
			list[start].add(new Node(end, money));
			list[end].add(new Node(start, money));
		}
		
		// A번 노드랑 연결된 것 만큼 반복
		visit = new boolean[N + 1];
		visit[A] = true;
		dfs(A, 0, 0);
		
		
		if (answer == Integer.MAX_VALUE) {
			System.out.println(-1);
			return;
		}
		
		System.out.println(answer);
		
	}

	private static void dfs(int start, int sum, int max) {
		// 도착지점에 방문했을 경우
		if (start == B) {
			answer = Math.min(answer, max);
			return;
		}
		
		for (Node node : list[start]) {		
			if (node.cost + sum <= C) {
				if (!visit[node.end]) {
					visit[node.end] = true;
					sum += node.cost;
					
					dfs(node.end, sum, Integer.max(node.cost, max));
					
					visit[node.end] = false;
					sum -= node.cost;
				
				}
				
			}
			
		}
		
	}

	
}

