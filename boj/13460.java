import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;
    static int[] dx = {-1, 0, 1, 0}; // clockwise from the top
    static int[] dy = {0, 1, 0, -1}; // clockwise from the top

    static class State {
        int rx, ry, bx, by, depth;
        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx; this.ry = ry;
            this.bx = bx; this.by = by;
            this.depth = depth;
        }
    }

    static class Move {
        int x, y;
        boolean inHole;
        Move(int x, int y, boolean inHole) {
            this.x = x; this.y = y; this.inHole = inHole;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        int rx = 0, ry = 0, bx = 0, by = 0;
        for(int i = 0 ; i < N ; i++) {
            String s = br.readLine();
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'R') { // save the location of the red ball
                    rx = i; ry = j;
                }
                if(map[i][j] == 'B') { // save the location of the blue ball
                    bx = i; by = j;
                }
            }
        }

        int answer = bfs(rx, ry, bx, by);
        System.out.print(answer);
        
    }

    static int bfs(int rx, int ry, int bx, int by) {
        Queue<State> q = new LinkedList<>();
        q.add(new State(rx, ry, bx, by, 0));
        visited[rx][ry][bx][by] = true;

        while(!q.isEmpty()) {
            State cur = q.poll();
            if(cur.depth >= 10) continue;

            for(int dir = 0 ; dir < 4; dir++) {
                boolean redFirst = moveRedFirst(cur, dir); // dicide which ball moves first

                Move first = redFirst 
                    ? roll(cur.rx, cur.ry, dir, -1, -1) 
                    : roll(cur.bx, cur.by, dir, -1, -1);

                Move second = redFirst 
                    ? roll(cur.bx, cur.by, dir, first.x, first.y)
                    : roll(cur.rx, cur.ry, dir, first.x, first.y);

                int nrx = redFirst ? first.x : second.x;
                int nry = redFirst ? first.y : second.y;
                int nbx = redFirst ? second.x : first.x;
                int nby = redFirst ? second.y : first.y;
                boolean redInHole = redFirst ? first.inHole : second.inHole;
                boolean blueInHole = redFirst ? second.inHole : first.inHole;

                if(blueInHole) continue;
                if(redInHole) return cur.depth + 1;

                if(!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    q.add(new State(nrx, nry, nbx, nby, cur.depth+1));
                }
            }
        }
        return -1; // more then 10 times
    }

    static Move roll(int x, int y, int dir, int ox, int oy) {
        boolean inHole = false;
        while(true) {
            int nx = x + dx[dir], ny = y + dy[dir];

            if(map[nx][ny] == 'O') {
                x = nx; y = ny;
                inHole = true;
                break;
            }
            
            if(map[nx][ny] == '#' || (nx == ox && ny == oy)) {
                break;
            }

            x = nx; y = ny;
        }
        return new Move(x, y, inHole);
    }

    static boolean moveRedFirst(State s, int dir) {
        if(dir == 0) return s.rx < s.bx; // up
        if(dir == 1) return s.ry > s.by; // right
        if(dir == 2) return s.rx > s.bx; // down
        return s.ry < s.by; // left
    }
}
