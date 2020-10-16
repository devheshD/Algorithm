package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int tc = Integer.parseInt(br.readLine());
    	
    	for (int test_case = 0; test_case < tc; test_case++) {
    		String[] input = br.readLine().split(" ");
    		int M = Integer.parseInt(input[0]);
    		int N = Integer.parseInt(input[1]);
    		int x = Integer.parseInt(input[2]);
    		int y = Integer.parseInt(input[3]);
    		// 최소공배수 (카잉 달력의 마지막 해)
    		int lcm = gcd(M, N) * (M / gcd(M, N) * (N / gcd(M, N)));
    		int answer = x;
    		int search_y = x;
    		int plusM = 0;
    		
    		for (int i = 0; i < N; i++) {
    			answer += plusM;
    			search_y = (search_y + plusM) % N;
    			
    			if (search_y == 0)
    				search_y = N;
    			
    			if (search_y == y) {
    				break;
    			}
    			
    			if (plusM == 0)
    				plusM = M;
    			
    		}
    		
    		if (answer > lcm) {
    			System.out.println("-1");
    		} else {
    			System.out.println(answer);
    		}
    		
    	}
    	
    }
    // 최대 공약수
	private static int gcd(int m, int n) {
		if (n == 0) {
			return m;
		}
		return gcd(n, m % n);
	}

}