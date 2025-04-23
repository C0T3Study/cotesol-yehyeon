import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] map;
    static int[][] time;
    static List<int[]> virus = new ArrayList<>();
    static List<int[]> active = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    static int room = 0;

    static int[] dx = {-1, 0, 1, 0}; // 위부터 시계방향
    static int[] dy = {0, 1, 0, -1}; // 위부터 시계방향
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        time = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) { // 바이러스인 경우
                    virus.add(new int[] {i, j});
                }
                if(map[i][j] == 0) { // 빈 방인 경우
                    room++;
                }
            }
        }

        if(room == 0) { // 빈칸의 수가 0이면 애초에 0
            System.out.println(0);
        } else {
            comb(0, 0); // 브루트포스
            if(ans == Integer.MAX_VALUE) System.out.println(-1);
            else System.out.println(ans);
        }

        
        
    }

    static void comb(int s, int d) {
        if (d == M) { // M만큼 뽑았으면 조합 끝내기
            bfs(room); // 해당 조합으로 bfs 시작
            return;
        }
        for(int i = s ; i < virus.size(); i++) { // 백트래킹으로 조합 구현
            active.add(virus.get(i));
            comb(i+1, d+1);
            active.remove(active.size() -1);
        }
    }

    static void bfs(int cnt) {
        Queue<int[]> q = new LinkedList<>();
        initTime(); // time 배열 초기화
        int remain = cnt; // 남은 빈 칸의 수

        for(int[] v : active) { // 선정된 M개의 바이러스
            q.add(v); // queue에 넣어주고
            time[v[0]][v[1]] = 0; // time은 0으로 초기화해주고 시작
        }

        int minTime = 0;

        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0 ; i < 4 ; i++) { // 위쪽부터 시계방향
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue; // 인덱스 검사
                if(map[nx][ny] == 1 || time[nx][ny] != -1) continue; // 벽인 경우, 방문한 적이 있는지 검사

                time[nx][ny] = time[now[0]][now[1]] + 1; // 전염 시간 업데이트
                if(map[nx][ny] == 0) { // 빈 칸인 경우에는
                    remain--; // 남은 빈 칸의 수 줄이기
                    minTime = time[nx][ny]; // minTime 업데이트
                }
                q.add(new int[]{nx, ny});
                
            }
        }

        if(remain == 0) ans = Math.min(ans, minTime); // 빈 칸이 없다면 ans 업데이트
        
    }

    static void initTime() { // time 배열 초기화
        for(int i = 0 ; i < N ; i++)
            for(int j = 0 ; j < N ; j++) 
                time[i][j] = -1;
    }
}
