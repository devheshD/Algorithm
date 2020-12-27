package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> queue = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) {
			String[] input = br.readLine().split(" ");
			
			switch (input[0]) {
			case "push":
				int number = Integer.parseInt(input[1]);
				queue.add(number);
				break;
			case "pop":
				if (queue.size() != 0) {
					System.out.println(queue.remove(0));
				} else {
					System.out.println("-1");
				}
				break;
			case "size":
				System.out.println(queue.size());
				break;
			case "empty":
				if (queue.size() == 0) {
					System.out.println("1");
				} else {
					System.out.println("0");
				}
				break;
			case "front":
				if (queue.size() != 0) {
					System.out.println(queue.get(0));
				} else {
					System.out.println("-1");
				}
				break;

			case "back":
				if (queue.size() != 0) {
					System.out.println(queue.get(queue.size() - 1));

				} else {
					System.out.println("-1");
				}
				break;

			}
						
		}
		
		
	}

}

