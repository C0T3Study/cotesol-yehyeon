import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, K, L;
    static int[][] map; // 사과 위치 저장할 배열
    static Map<Integer, String> move = new HashMap<>();
    static Queue<int[]> q = new LinkedList<>(); // 뱀의 위치 저장할 queue
    static int[] dx = {0, 1, 0, -1}; // 우하좌상
    static int[] dy = {1, 0, -1, 0}; // 우하좌상
    static int cnt = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보드 크기
        K = Integer.parseInt(br.readLine()); // 사과 위치 수
        map = new int[N+1][N+1];
        StringTokenizer st;ㄱ
        for(int i = 0 ; i < K ; i++) { // 사과의 위치 저장
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1; 
        }

        L = Integer.parseInt(br.readLine());
        for(int i = 0 ; i < L ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();
            move.put(x, c);
        }
        
        solve();
        System.out.println(cnt);
    }

    static void solve() {
        int time = 0;
        int x = 1, y = 1;
        int d = 0;
        q.add(new int[] {1, 1}); // 뱀의 처음 위치 queue에 저장

        while(true) {
            // 1. 시간 흐름
            time++; 

            // 뱀 위치 이동
            int nx = x + dx[d];
            int ny = y + dy[d];

            // 2. 게임이 끝났는지 검사하기
            if(nx < 1 || ny < 1 || nx > N || ny > N) break;
            boolean isEnd = false;
            for(int[] snake : q) {
                if(snake[0] == nx && snake[1] == ny) {
                    isEnd = true;
                    break;
                }
            }
            if(isEnd) break;

            // 3. 뱀 이동하기
            if(map[nx][ny] == 1) { // 사과 있는 경우
                map[nx][ny] = 0; 
                q.add(new int[] {nx, ny});
            } else { // 사과 없는 경우
                q.add(new int[] {nx, ny});
                q.poll();
            }

            // 4. 방향 전환
            if(move.containsKey(time)) {
                String dir = move.get(time);
                if(dir.equals("D")) {
                    d++;
                    if(d==4) d=0; // 범위 초과인 경우 다시 0으로
                } else {
                    d--;
                    if(d==-1) d=3; // 범위 초과인 경우 다시 3으로 
                    
                }
            }
            x = nx;
            y = ny;
        }
        cnt = time;
        
    }
}
