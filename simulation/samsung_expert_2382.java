package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Info implements Comparable<Info>{
	int l, h;
	
	public Info(int l, int h) {
		this.l = l;
		this.h = h;
	}

	@Override
	public int compareTo(Info o) {
		return this.l - o.l;
	}
	
}

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		List<Info> list = new ArrayList<Info>();
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			int L = Integer.parseInt(input[0]);
			int H = Integer.parseInt(input[1]);
		
			list.add(new Info(L, H));
		}
		
		Collections.sort(list);
		
		List<Info> left = new ArrayList<Info>();
		for (int i = 0; i < n; i++) {
			if (left.isEmpty() || list.get(i).h > left.get(left.size() - 1).h) {
				left.add(list.get(i));
			}
		}
		
		List<Info> right = new ArrayList<Info>();
		for (int i = n - 1; i >= 0; i--) {
			if (right.isEmpty() || list.get(i).h > right.get(right.size() - 1).h) {
				right.add(list.get(i));
			}
		}
		
		int answer = 0;
		for (int i = 0; i < left.size() - 1; i++) {
			answer += (left.get(i + 1).l - left.get(i).l) * left.get(i).h;
		}
	
		for (int i = 0; i < right.size() - 1; i++) {
			answer += (right.get(i).l - right.get(i + 1).l) * right.get(i).h;	
		}
		
		answer += Math.abs(left.get(left.size() - 1).l - right.get(right.size() - 1).l) * right.get(right.size() - 1).h;
		answer += right.get(right.size() - 1).h;
		
		
		System.out.println(answer);
		
	}

}

