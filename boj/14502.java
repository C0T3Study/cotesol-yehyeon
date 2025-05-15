import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] selected;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] wall = new int[3][2];
    static int max = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        selected = new boolean[N][M];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0);
        System.out.println(max);
        
        
    }

    static void comb(int depth) {
        if(depth == 3) {
            bfs();
            return;
        }

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(!selected[i][j] && map[i][j] == 0) {
                    selected[i][j] = true;
                    wall[depth][0] = i; wall[depth][1] = j;
                    comb(depth+1);
                    selected[i][j] = false;
                }
            }
        }
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[][] simul = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        
        for(int i = 0 ; i < N ; i++) {
            simul[i] = map[i].clone();
        }

        for(int i = 0 ; i < 3; i++) {
            int x = wall[i][0]; int y = wall[i][1];
            simul[x][y] = 1;
        }

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(simul[i][j] == 2) q.add(new int[] {i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for(int i = 0 ; i < 4 ; i++) {
                int nx = cur[0] + dx[i]; int ny = cur[1] + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M
                  && !visited[nx][ny]
                  && simul[nx][ny] == 0) {
                    simul[nx][ny] = 2;
                    q.add(new int[] {nx, ny});
                    visited[nx][ny] = true;
                  }
            }
            
        }
        
        int cnt = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(simul[i][j] == 0) cnt++;
            }
        }
        max = Math.max(cnt, max);
        
    }
    
}
