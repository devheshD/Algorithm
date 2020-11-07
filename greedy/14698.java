package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
	static final int MOD = 1000000007;
	static int N;
	static PriorityQueue<Long> energy;
	static ArrayList<Long> cost; 
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < tc; test_case++) {
			N = Integer.parseInt(br.readLine());		// 슬라임의 수
			String[] input = br.readLine().split(" ");
			energy = new PriorityQueue<Long>();				// 각 슬라임의 에너지
			
			for (int i = 0; i < N; i++) {
				energy.add(Long.parseLong(input[i]));
			}
			
			cost = new ArrayList<Long>();				// 슬라임 합성에 필요한 전기에너지 비용
			
			while (energy.size() > 1) {
				long money = energy.poll() * energy.poll();		// 전기에너지를 곱한 값을 리스트에 저장
				// 합성 비용 저장
				cost.add(money);
				// 합성된 새로운 슬라임 추가
				energy.add(money);
			}
			// 사용된 비용 곱하기
			BigInteger answer = BigInteger.ONE;
		
			for (int i = 0; i < cost.size(); i++) {
				answer = answer.multiply(BigInteger.valueOf(cost.get(i)));
			}
			
			answer = answer.remainder(BigInteger.valueOf(MOD));
			
			bw.write(answer.toString());
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}
	
}

