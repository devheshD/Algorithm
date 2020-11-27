package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int n, m, answer, cycleCnt;
	static boolean[] visit;
	static List<Integer>[] list;
	static boolean isCycle;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input;
		int test_case = 1;
		
		while (true) {
			input = br.readLine().split(" ");
			n = Integer.parseInt(input[0]);
			m = Integer.parseInt(input[1]);
			// 입력값이 0, 0이면 종료
			if (n == 0 && m == 0) {
				break;
			}
		
			list = new ArrayList[n + 1];
			for (int i = 1; i <= n; i++) {
				list[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < m; i++) {
				input = br.readLine().split(" ");
				int node1 = Integer.parseInt(input[0]);
				int node2 = Integer.parseInt(input[1]);
				list[node1].add(node2);
				list[node2].add(node1);
				
			}
			
			visit = new boolean[n + 1];
			
			for (int node = 1; node <= n; node++) {
				if (!visit[node]) {
					isCycle = false;
					
					dfs(0, node);
					
					if (!isCycle) {
						answer++;
					} 
				
				}
			}
			
			if (answer == 0) {
				bw.write("Case " + test_case + ": " + "No trees.");
			} else if (answer == 1) {
				bw.write("Case " + test_case + ": " + "There is one tree.");
			} else {
				bw.write("Case " + test_case + ": " + "A forest of " + answer + " trees.");
			}
			
			bw.newLine();
			test_case++;
			answer = 0;
			cycleCnt = 0;
		}
		
		bw.flush();
		bw.close();
		
	}

	private static void dfs(int before, int node) {
		visit[node] = true;
		
		for (int i = 0; i < list[node].size(); i++) {
			int next = list[node].get(i);
			
			if (before != next) {
				if (!visit[next]) {
					dfs(node, next);
				} else {
					isCycle = true;
				}
			}
			
		}
		
	}

}