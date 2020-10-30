package com.baekjoon.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();
        Queue<Character> waittingQ = new LinkedList<Character>();
        int manCnt = 0;
        int womanCnt = 0;

        for (int i = 0; i < input.length(); i++) {
            char person = input.charAt(i);

            if (!waittingQ.isEmpty()) {
                char waittingPerson = waittingQ.peek();
                if (waittingPerson == 'M') {
                    if (Math.abs((manCnt + 1) - womanCnt) <= n) {
                        manCnt++;
                        waittingQ.poll();
                    }
                } else {
                    if (Math.abs(manCnt - (womanCnt + 1)) <= n) {
                        womanCnt++;
                        waittingQ.poll();
                    }
                }
            }

            if (person == 'M') {
                // 남자와 여자 차이가 n이하라면
                if (Math.abs((manCnt + 1) - womanCnt) <= n) {
                    manCnt++;
                }
                // 대기선이 비어있다면
                else if (waittingQ.isEmpty()) {
                    waittingQ.add(person);
                } 
                // 차이가 n초과이고 대기선도 비어있지않다면
                else {
                    break;
                }
            } else {
                if (Math.abs(manCnt - (womanCnt + 1)) <= n) {
                    womanCnt++;
                }  else if (waittingQ.isEmpty()) {
                    waittingQ.add(person);
                } else {
                    break;
                }
            }

        }

        System.out.println(manCnt + womanCnt);


    }

}