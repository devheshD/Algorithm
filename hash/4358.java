package com.baekjoon.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		HashMap<String, Double> map = new HashMap<String, Double>();	// key : 나무 이름, value : 해당 나무 개수
		List<String> tree = new ArrayList<String>();					// 나무 이름 저장
		
		double totalCnt = 0;		// 입력받은 총 개수
		while (scan.hasNextLine()) {
			String input = scan.nextLine();
			// 처음 들어있는 값인 경우
			if (map.get(input) == null) {
				map.put(input, 1.0);
				tree.add(input);
			} 
			// 기존에 존재할경우 갯수 증가
			else {
				map.put(input, map.get(input) + 1.0);
			}
			
			totalCnt++;
		}
		// 사전순 정렬 (오름차순)
		Collections.sort(tree);
		
		for (int i = 0; i < tree.size(); i++) {
			double cnt = map.get(tree.get(i));
			System.out.print(tree.get(i) + " ");
			System.out.printf("%.4f\n", cnt / totalCnt * 100);
		}
		
		scan.close();
		
	}
	

}

