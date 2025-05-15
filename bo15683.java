import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, M;
    static int[][] map;
    static int[] lookDir;
    static int[] dx = {-1, 0, 1, 0}; // 위부터 시계방향
    static int[] dy = {0, 1, 0, -1}; // 위부터 시계방향
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        int cnt = 0;
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6) cnt++;
            }
        }
        lookDir = new int[cnt];

        comb(0);
        System.out.print(min);

        
        
    }

    static void comb(int depth) {
        if(depth == lookDir.length) {
            countUnsafeZone();
            return;
        }

        for(int i = 0 ; i < 4 ; i++) {
            lookDir[depth] = i;
            comb(depth+1);
        }
    }

    static void countUnsafeZone() {
        int[][] copyMap = new int[N][M];
        for(int i = 0 ; i < N ; i++) { copyMap[i] = map[i].clone(); }

        int index = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(copyMap[i][j] > 0 && copyMap[i][j] < 6) {

                    if(copyMap[i][j] == 1) {
                        if(lookDir[index] == 0) look(copyMap, i, j, 0);
                        else if(lookDir[index] == 1) look(copyMap, i, j, 1);
                        else if(lookDir[index] == 2) look(copyMap, i, j, 2);
                        else look(copyMap, i, j, 3);
                    } else if(copyMap[i][j] == 2) {
                        if(lookDir[index] == 0 || lookDir[index] == 2) {
                            look(copyMap, i, j, 0);
                            look(copyMap, i, j, 2);
                        } else {
                            look(copyMap, i, j, 1);
                            look(copyMap, i, j, 3);
                        }
                    } else if(copyMap[i][j] == 3) {
                        if(lookDir[index] == 0) {
                            look(copyMap, i, j, 0);
                            look(copyMap, i, j, 1);
                        } else if(lookDir[index] == 1) {
                            look(copyMap, i, j, 1);
                            look(copyMap, i, j, 2);
                        } else if(lookDir[index] == 2) {
                            look(copyMap, i, j, 2);
                            look(copyMap, i, j, 3);
                        } else {
                            look(copyMap, i, j, 3);
                            look(copyMap, i, j, 0);
                        }
                    } else if(copyMap[i][j] == 4) {
                        if(lookDir[index] == 0) {
                            look(copyMap, i, j, 0);
                            look(copyMap, i, j, 1);
                            look(copyMap, i, j, 2);
                        } else if(lookDir[index] == 1) {
                            look(copyMap, i, j, 1);
                            look(copyMap, i, j, 2);
                            look(copyMap, i, j, 3);
                        } else if(lookDir[index] == 2) {
                            look(copyMap, i, j, 2);
                            look(copyMap, i, j, 3);
                            look(copyMap, i, j, 0);
                        } else {
                            look(copyMap, i, j, 3);
                            look(copyMap, i, j, 0);
                            look(copyMap, i, j, 1);
                        }
                    } else {
                        look(copyMap, i, j, 0);
                        look(copyMap, i, j, 1);
                        look(copyMap, i, j, 2);
                        look(copyMap, i, j, 3);
                    }
                    
                    index++;
                }
            }
        }

        int cnt = 0;
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < M ; j++) {
                if(copyMap[i][j] == 0) cnt++;
            }
        }
        min = Math.min(min, cnt);
    }

    static void look(int[][] copyMap, int x, int y, int dir) {
        while(true) {
            int nx = x + dx[dir], ny = y + dy[dir];
            if(nx < 0 || nx >= N || ny < 0 || ny >= M) break;
            if(copyMap[nx][ny] == 6) break; // 벽 만나면 끝

            if(copyMap[nx][ny] == 0) { // 빈 공간이라면
                copyMap[nx][ny] = -1; // 감시 중으로 변경
                x = nx; y = ny;
            } else {
                x = nx; y = ny; // cctv 있으면 통과
                continue;
            }
        }
    }
    
}
