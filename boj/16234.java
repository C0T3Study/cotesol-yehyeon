import java.util.*;
import java.lang.*;
import java.io.*;

class Country {
    int r, c;

    public Country(int r, int c) {
        this.r = r; this.c = c;
    }
}

// The main method must be in a class named "Main".
class Main {
    static int N, L, R;
    static int[][] A;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static ArrayList<Country> l;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = move();
        System.out.println(cnt);
    }

    static int move() {
        int res = 0;
        while(true) {
            boolean isMove = false;
            visited = new boolean[N][N];
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(!visited[i][j]) {
                        int sum = bfs(i, j);
                        if(l.size() > 1) {
                            change(sum);
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove) return res;
            res++;
        }
    }

    static int bfs(int r, int c) {
        Queue<Country> q = new LinkedList<>();
        l = new ArrayList<>();

        q.offer(new Country(r, c));
        l.add(new Country(r, c));
        visited[r][c] = true;

        int sum = A[r][c];
        while(!q.isEmpty()) {
            Country cur = q.poll();

            for(int i = 0 ; i < 4 ; i++) {
                int nr = cur.r + dr[i]; int nc = cur.c + dc[i];
                if(nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
                    if(Math.abs(A[cur.r][cur.c] - A[nr][nc]) >= L
                      && Math.abs(A[cur.r][cur.c] - A[nr][nc]) <= R) {
                        q.offer(new Country(nr, nc));
                        l.add(new Country(nr, nc));
                        sum += A[nr][nc];
                        visited[nr][nc] = true;
                      }
                }
            }
        }
        return sum;
    }

    static void change(int sum) {
        int avg = sum / l.size();
        for(Country c : l) {
            A[c.r][c.c] = avg;
        }
    }


}
