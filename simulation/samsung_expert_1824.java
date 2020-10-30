import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static boolean flag;
    static boolean[][][][] visit;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        // 테스트 케이스
        for (int test_case = 1; test_case <= tc; test_case++) {
            String[] input1 = br.readLine().split(" ");
            R = Integer.parseInt(input1[0]);
            C = Integer.parseInt(input1[1]);
            map = new char[R][C];

            // 혁진이 프로그램 입력
            for (int row = 0; row < R; row++) {
                String input2 = br.readLine();
                for (int col = 0; col < C; col++) {
                    map[row][col] = input2.charAt(col);
                }

            }
            flag = false;
            visit = new boolean[R][C][4][16];
            dfs(0, 0, 1, 0);
            if (flag) {
                System.out.println("#" + test_case + " YES");
            } else {
                System.out.println("#" + test_case + " NO");
            }

        }

    }

private static void dfs(int row, int col, int dir, int memory) {
    char ch = map[row][col];
    if (flag) {
        return;
    }
    
    if (visit[row][col][dir][memory]) 
        return;
    
    visit[row][col][dir][memory] = true;
    
    if (ch == '?') {
        for (int i = 0; i < 4; i++) {
            visit[row][col][i][memory] = true;
            int nr = row, nc = col;
            
            if (nr + dx[i] == R) {
                nr = 0;
            } else if (nr + dx[i] == -1) {
                nr = R - 1;
            } else if (nc + dy[i] == C) {
                nc = 0;
            } else if (nc + dy[i] == -1) {
                nc = C - 1;
            } else {
                nr = nr + dx[i];
                nc = nc + dy[i];
            }
            
            dfs(nr, nc, i, memory);
            
        }
    
    } else {
        if (ch == '<') {
            dir = 3;
        } else if (ch == '>') {
            dir = 1;
        } else if (ch == '^') {
            dir = 0;
        } else if (ch == 'v') {
            dir = 2;
        } else if (ch == '_') {
            if (memory == 0) {
                dir = 1;
            } else {
                dir = 3;
            }
        } else if (ch == '|') {
            if (memory == 0) {
                dir = 2;
            } else {
                dir = 0;
            }
        } else if (ch == '@') {
            flag = true;
            return;
        } else if (0 <= ch - '0' && ch - '0' <= 9) {
            memory = ch - '0';
        } else if (ch == '+') {
            if (memory == 15) {
                memory = 0;
            } else {
                memory += 1;
            }
        } else if (ch == '-') {
            if (memory == 0) {
                memory = 15;
            } else {
                memory -= 1;
            }
        }
    }
    
    if (row + dx[dir] == R) {
        row = 0;
    } else if (row + dx[dir] == -1) {
        row = R - 1;
    } else if (col + dy[dir] == C) {
        col = 0;
    } else if (col + dy[dir] == -1) {
        col = C - 1;
    } else {
        row = row + dx[dir];
        col = col + dy[dir];
    }

    dfs(row, col, dir, memory);
    
}
}
