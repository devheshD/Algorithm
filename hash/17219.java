package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	static String[] input;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);			// 저장된 사이트 주소의 수
		int M = Integer.parseInt(input[1]);			// 비밀번호를 찾으려는 사이트 주소의 수
		
		// 각 줄의 사이트 주소와 비밀번호 입력 
		HashMap<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < N; i++) {
			input = br.readLine().split(" ");
			String site = input[0];
			String password = input[1];
			map.put(site, password);
		}
		
		//	해당 사이트에 대한 비밀번호(value) 출력 
		for (int i = 0; i < M; i++) {
			String inputSite = br.readLine();
			System.out.println(map.get(inputSite));
		}
		
		
	}

}

