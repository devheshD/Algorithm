package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
	static String[] input;
	static int N, saveStart;
	static int[] array;
	static boolean[] check;
	static List<Integer> answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		array = new int[N + 1];
		check = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			array[i] = Integer.parseInt(br.readLine());
		}
		
		answer = new ArrayList<Integer>();
		// 처음부터 탐색
		for (int i = 1; i <= N; i++) {
			check[i] = true;
			// 시작 지점 저장
			saveStart = i;
			dfs(i);
			check[i] = false;
		}
		
		Collections.sort(answer);
		System.out.println(answer.size());
		for (int num : answer) {
			System.out.println(num);
		}
		
	}

	private static void dfs(int index) {
		// 체크가 안된경우
		if (!check[array[index]]) {
			check[array[index]] = true;
			dfs(array[index]);
			check[array[index]] = false;
		}
		// 시작했던 지점에 도착할 경우
		if (array[index] == saveStart) {
			answer.add(saveStart);
		}
		
	}

}

