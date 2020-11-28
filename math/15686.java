package com.baekjoon.java;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int N, M;
	static int[][] map;
	static List<Point> store, house, select;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	String[] input; 
    	input = br.readLine().split(" ");
    	N = Integer.parseInt(input[0]);
    	M = Integer.parseInt(input[1]);
    	map = new int[N][N];
    	store = new ArrayList<Point>();
    	house = new ArrayList<Point>();
    	select = new ArrayList<Point>();
    	
    	for (int row = 0; row < N; row++) {
    		input = br.readLine().split(" ");
    		for (int col = 0; col < N; col++) {
    			map[row][col] = Integer.parseInt(input[col]);
    			// 치킨집 좌표 저장
    			if (map[row][col] == 2) {
    				store.add(new Point(row, col));
    			} 
    			// 일반집 좌표 저장
    			else if (map[row][col] == 1) {
    				house.add(new Point(row, col));
    			}
    			
    		}
    	}
    	
    	combination(0, 0);
    	
    	System.out.println(answer);
    	
	}

	private static void combination(int cnt, int start) {
		if (cnt == M) {
			calc();
			return;
		}
		
		for (int i = start; i < store.size(); i++) {
			select.add(store.get(i));
			combination(cnt + 1, i + 1);
			select.remove(select.size() - 1);
		}
		
		
	}

	private static void calc() {
		int sum = 0;
		
		// 치킨집과 일반집의 거리를 구하기
		for (int i = 0; i < house.size(); i++) {
			int min = Integer.MAX_VALUE;
			Point housePoint = house.get(i);
			int house_x = housePoint.x;
			int house_y = housePoint.y;
			
			// 선택된 치킨집
			for (int j = 0; j < select.size(); j++) {
				Point chickenPoint = select.get(j);
				int chicken_x = chickenPoint.x;
				int chicken_y = chickenPoint.y;
				int distance = Math.abs(chicken_x - house_x) + Math.abs(chicken_y - house_y);
				// 치킨집과 일반집 차이의 거리가 가장 가까운곳 저장
				min = Math.min(min, distance);
				
			}
			
			sum += min;
		}
		
		answer = Math.min(answer, sum);
	}

	
}
