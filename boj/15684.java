import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, H, ans;
    static int[][] map;
    static boolean finish = false;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[N+1][H+1];
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[b][a] = 1; map[b+1][a] = -1;
        }

        for(int i = 0 ; i <= 3; i++) {
            ans = i;
            addLine(0);
            if(finish) break;
        }

        System.out.print(finish ? ans : -1);

        
        
    }

    static void addLine(int depth) {
        if(finish) return;
        if(ans == depth) {
            if(isTrue()) finish = true;
            return;
        }

        for(int i = 1 ; i < N ; i++) {
            for(int j = 1 ; j <= H ; j++) {
                if(map[i][j] == 0 && map[i+1][j] == 0) {
                    map[i][j] = 1; map[i+1][j] = -1;
                    addLine(depth+1);
                    map[i][j] = map[i+1][j] = 0;
                }
            }
        }
    }


    static boolean isTrue() { // 사다리 타서 정답인지 검사
        for(int i = 1 ; i <= N ; i++) { // 1번 세로줄부터 사다리 타기
            int cur = i;
            for(int j = 1 ; j <= H ; j++) {
                if(map[cur][j] == 1) cur += 1;
                else if (map[cur][j] == -1) cur -= 1;
            }
            if(cur != i) return false; // i번 -> i번이 아니면 정답이 아님
        }

        return true;
    }
    
}
