package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		BigInteger base = new BigInteger("2");
		// 2^n - 1
		base = base.pow(n).subtract(BigInteger.ONE);
		System.out.println(base);
		
		if (n <= 20)
			hanoi(n, 1, 2, 3);
		
	
	}

	private static void hanoi(int n, int from, int by, int to) {
		if (n == 1) {
			System.out.println(from + " " + to);
		} else {
			hanoi(n - 1, from, to, by);
			System.out.println(from + " " + to);
			hanoi(n - 1, by, from, to);
		}
		
	}

}

