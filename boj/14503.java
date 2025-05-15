import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int r, c, d;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int cnt = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if(map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }
            
            boolean isTrue = false;
            for(int i = 0 ; i < 4 ; i++) {
                int nr = r + dr[i]; int nc = c + dc[i];
                if(map[nr][nc] == 0) isTrue = true; 
            }

            if(!isTrue) { // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
                int nr = r + dr[d]*(-1); int nc = c + dc[d]*(-1);
                
                if(map[nr][nc] == 1) break; // 2-2. 바라보는 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                else { r = nr; c = nc; } // 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
            } else { // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                // 3-1. 반시계 방향으로 90도 회전한다.
                d--;
                if(d == -1) d=3;
                
                // 3-2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                int nr = r + dr[d]; int nc = c + dc[d];
                if(map[nr][nc] == 0) { r = nr; c = nc; }
            }
        }

        System.out.println(cnt);
        
    }
}
