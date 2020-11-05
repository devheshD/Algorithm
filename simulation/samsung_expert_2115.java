package com.samsung.expert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Character.Subset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    static int N, M, C, answer, sumPerson1, sumPerson2;
    static int[][] map;
    static int[] person1, person2;
    static boolean[][] visited;
    static boolean[] sel1, sel2;

 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int tc = Integer.parseInt(br.readLine());
        String[] input;
        for (int test_case = 1; test_case <= tc; test_case++) {
            input = br.readLine().split(" ");
            N = Integer.parseInt(input[0]);
            M = Integer.parseInt(input[1]);
            C = Integer.parseInt(input[2]);
            map = new int[N][N];
            person1 = new int[M];
            person2 = new int[M];
           sumPerson1 = 0;
            sumPerson2 = 0;
            
            for (int row = 0; row < N; row++) {
            	input = br.readLine().split(" ");
            	for (int col = 0; col < N; col++) {
            		map[row][col] = Integer.parseInt(input[col]);
            	}
            }
            
            for (int row = 0; row < N - 1; row++) {
            	for (int col = 0; col <= N - M; col++) {
            		select1(row, col);

            		select2();
            	}
            }
            
           
            bw.write("#" + test_case + " " + answer);
            bw.newLine();
            answer = 0;

        }

        bw.flush();
        bw.close();

    }
    

	// 두번째 사람이 뽑을 수 있는 경우의 수 구하기
    private static void select2() {
    	for (int row = 0; row < N; row++) {
        	for (int col = 0; col <= N - M; col++) {
        		// person1이 이미 꿀을 가져간 경우
        		if (visited[row][col]) continue;
        		
        		else {
        			// 연속되는 꿀벌인지 확인
        			if (check(row, col)) {
        				int prevCol = col;
            			for (int i = 0; i < M; i++) {
            				person2[i] = map[row][prevCol];
            				visited[row][prevCol++] = true;
            			}
            			
            			// 벌꿀 추출
            			extract();
  
                    	
            			prevCol = col;
            			
            			for (int i = 0; i < M; i++) {
            				visited[row][prevCol++] = false;
            			}
        			}
        			
        		}
        		
        	}
        }
    	
	}
    
	private static void extract() {	
		 sel1 = new boolean[M];
         sel2 = new boolean[M];
         sumPerson1 = 0;
         sumPerson2 = 0;
		subset(0);

		subset2(0);
		answer = Math.max(sumPerson1 + sumPerson2, answer);
	
	}
	
	private static void subset2(int cnt) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				if (sel2[i]) {
					sum += person2[i];
				}
			}

			if (sum <= C) {
				sum = 0;
				for (int i = 0; i < M; i++) {
					if (sel2[i]) {
						sum += (person2[i] * person2[i]);
					}
				}
				
				sumPerson2 = Math.max(sumPerson2, sum);
			
			}
			return;
		}
		
		subset2(cnt + 1);
		sel2[cnt] = true;
		
		subset2(cnt + 1);
		sel2[cnt] = false;
		
	}

	private static void subset(int cnt) {
		if (cnt == M) {
			int sum = 0;
			for (int i = 0; i < M; i++) {
				if (sel1[i]) {
					sum += person1[i];
				}
			}
			if (sum <= C) {
				sum = 0;
				for (int i = 0; i < M; i++) {
					if (sel1[i]) {
						sum += (person1[i] * person1[i]);
					}
				}
				sumPerson1 = Math.max(sumPerson1, sum);
			}
			return;
		}
		
		subset(cnt + 1);
		sel1[cnt] = true;
		
		subset(cnt + 1);
		sel1[cnt] = false;
		
	}


	private static boolean check(int row, int col) {
		for (int i = 0; i < M; i++) {
			if (visited[row][col++]) {
				return false;
			}
		}
		
		return true;
	}


	// 첫번째 사람이 뽑을 수 있는 경우의 수 구하기
    private static void select1(int row, int col) {
		visited = new boolean[N][N];

		for (int i = 0; i < M; i++) {
			person1[i] = map[row][col];
			visited[row][col++] = true;
		}

	}

 
	

}

