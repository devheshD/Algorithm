package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int N, M, K;
    static int[] info;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);        		// 가로의 길이
        M = Integer.parseInt(input[1]);       		// 세로의 길이
        K = Integer.parseInt(br.readLine());        // 상점의 개수
        info = new int[K + 1];
        int totalDistance = (N + M) * 2;

        // 상점의 위치 정보 및 동근이의 위치 정보
        for (int i = 0; i < K + 1; i++) {
            input = br.readLine().split(" ");
            int direction = Integer.parseInt(input[0]);
            int distance  = Integer.parseInt(input[1]);
            info[i] = calc(direction, distance);
        }
        // 동근이의 시계방향 거리
        int cur = info[K];
        int answer = 0;

        for (int i = 0; i < K; i++) {
            // Math.abs(cur - info[i]) => 시계 방향으로 갔을때 거리 차이
            int oppositeDistance = totalDistance - Math.abs(cur - info[i]);
            answer += Math.min(oppositeDistance, Math.abs(cur - info[i]));
        }

        System.out.println(answer);
    }

	// 시계 방향으로 돌았을 때 계산
	private static int calc(int direction, int distance) {
	    int num = 0;
	    switch (direction) {
	    case 1:
	        num = distance;
	        break;
	    case 2:
	        num = N + M + N - distance;
	        break;
	    case 3:
	        num = N + M + N + M - distance;
	        break;
	    case 4:
	        num = N + distance;
	        break;
	    }
	    
	    return num;
	    
	}
}