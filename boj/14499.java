import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    static int[] dice = new int[6];
    static int[] move;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        move = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < K ; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        
    }

    static void solve() {
        for(int i = 0 ; i < K ; i++) {
            int nx = x + dx[move[i]-1];
            int ny = y + dy[move[i]-1];
            
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) { // 이동할 수 없는 경우
                continue;
            }

            if(move[i] == 1) { // 동
                int tmp = dice[0];
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[1];
                dice[1] = tmp;
            } else if(move[i] == 2) { // 서
                int tmp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[2];
                dice[2] = tmp;
            } else if(move[i] == 3) { // 남
                int tmp = dice[0];
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[3];
                dice[3] = tmp;
            } else { // 북
                int tmp = dice[0];
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[4];
                dice[4] = tmp;
            }

            if(map[nx][ny] == 0) { // 0이면 주사위의 바닥면 수 칸에 복사
                map[nx][ny] = dice[5];
            } else { // 0이 아니면 칸에 쓰여있는 수가 주사위에 복사
                dice[5] = map[nx][ny];
                map[nx][ny] = 0;
            }
            
            x = nx; y = ny;

            System.out.println(dice[0]); // 주사위 상단 출력
            
        }
    }
    
}
