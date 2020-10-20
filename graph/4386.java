package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class InputInfo {
	int no;
	double x;
	double y;
	
	public InputInfo(int no, double x, double y) {
		this.no = no;
		this.x = x;
		this.y = y;
	}
}

class Star {
	int node1;
	int node2;
	double cost;
	
	public Star(int node1, int node2, double cost) {
		this.node1 = node1;
		this.node2 = node2;
		this.cost = cost;
	}
}

public class Main {
	static int n;
	static int[] parents, rank;
	static List<Star> star_list;
	
	public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	star_list = new ArrayList<Star>();
    	List<InputInfo> list = new ArrayList<InputInfo>();
    	
    	int no = 0;
    	for (int i = 0; i < n; i++) {
    		String[] input = br.readLine().split(" ");
    		double x = Double.parseDouble(input[0]);
    		double y = Double.parseDouble(input[1]);
    		list.add(new InputInfo(no++, x, y));
    	}
    	
    	for (int i = 0; i < list.size() - 1; i++) {
    		int no1 = list.get(i).no;
    		double x1 = list.get(i).x;
    		double y1 = list.get(i).y;
    		
    		for (int j = i + 1; j < list.size(); j++) {
    			int no2 = list.get(j).no;
    			double x2 = list.get(j).x;
        		double y2 = list.get(j).y;
        		calc(no1, x1, y1, no2, x2, y2);
    		}
    	}
    	
    	Collections.sort(star_list, new Comparator<Star>() {

			@Override
			public int compare(Star o1, Star o2) {
				return (int) (o1.cost - o2.cost);
			}
		});
    
    	make_set();
    	
    	double answer = 0.0d;
    	for (int i = 0; i < star_list.size(); i++) {
    		int node1 = star_list.get(i).node1;
    		int node2 = star_list.get(i).node2;
    		double cost = star_list.get(i).cost;
    		
    		if (find(node1) != find(node2)) {
    			union(node1, node2);
    			answer += cost;
    		}
    	}
    	
    	System.out.println(String.format("%.2f", answer));
    	
	}

	
	private static void union(int node1, int node2) {
		int x = find(node1);
		int y = find(node2);
		
		if (rank[x] > rank[y]) {
			parents[y] = x;
		} else if (rank[x] < rank[y]) {
			parents[x] = y;
		} else {
			parents[x] = y;
			rank[y]++;
		}
		
	}


	private static int find(int node) {
		if (parents[node] == node) {
			return node;
		}
		
		return find(parents[node]);
	}


	private static void make_set() {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
		
		rank = new int[n];
		for (int i = 0; i < n; i++) {
			rank[i] = 0;
		}
	}


	// 두 좌표간의 거리 값 저장
	private static void calc(int no1, double x1, double y1, int no2, double x2, double y2) {
		int xd = (int) Math.pow((x1 - x2), 2);
    	int yd = (int) Math.pow((y1 - y2), 2);
    	double cost = Math.sqrt(xd + yd);
    
    	star_list.add(new Star(no1, no2, cost));
	}




}
