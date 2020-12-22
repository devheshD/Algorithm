package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static List<Integer>[] list;
	static int[][] matrix, answer;
	static boolean[] visit;
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList[n + 1];
		matrix = new int[n + 1][n + 1];
		answer = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			list[i] = new ArrayList<Integer>();
		}
		
		for (int row = 0; row < n; row++) {
			String[] input = br.readLine().split(" ");
			for (int col = 0; col < n; col++) {
				int num = Integer.parseInt(input[col]);
				matrix[row + 1][col + 1] = num;
				
				if (num == 1) {
					list[row + 1].add(col + 1);
				}
			}
		}
		
		for (int row = 1; row <= n; row++) {
			visit = new boolean[n + 1];
			for (int col = 1; col <= n; col++) {
				if (matrix[row][col] == 1 && !visit[row]) {
					dfs(row, row);
				}
			}
		}
		// 결과 출력
		print();
	}

	private static void print() {
		for (int row = 1; row <= n; row++) {
			for (int col = 1; col <= n; col++) {
				System.out.print(answer[row][col] + " ");
			}
			System.out.println();
		}
		
	}
	
	private static void dfs(int init, int start) {
		for (int leaf : list[start]) {
			if (!visit[leaf]) {
				visit[leaf] = true;
				answer[init][leaf] = 1;
				dfs(init, leaf);
			} 
		}
		
	}

	
}

