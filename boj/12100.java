import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int max = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.println(max);
    }

    static void dfs(int depth, int[][] map) {
        if (depth == 5) {
            findMax(map);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] copied = copyMap(map);
            int[][] moved = move(dir, copied);
            dfs(depth + 1, moved);
        }
    }

    static int[][] move(int dir, int[][] map) {
        int[][] newMap = new int[N][N];
        boolean[][] merged = new boolean[N][N];

        if (dir == 0) { // up
            for (int j = 0; j < N; j++) {
                for (int i = 1; i < N; i++) {
                    if (map[i][j] == 0) continue;
                    int x = i;
                    while (x > 0 && map[x - 1][j] == 0) {
                        map[x - 1][j] = map[x][j];
                        map[x][j] = 0;
                        x--;
                    }
                    if (x > 0 && map[x - 1][j] == map[x][j] && !merged[x - 1][j] && !merged[x][j]) {
                        map[x - 1][j] *= 2;
                        map[x][j] = 0;
                        merged[x - 1][j] = true;
                    }
                }
            }
        } else if (dir == 1) { // down
            for (int j = 0; j < N; j++) {
                for (int i = N - 2; i >= 0; i--) {
                    if (map[i][j] == 0) continue;
                    int x = i;
                    while (x < N - 1 && map[x + 1][j] == 0) {
                        map[x + 1][j] = map[x][j];
                        map[x][j] = 0;
                        x++;
                    }
                    if (x < N - 1 && map[x + 1][j] == map[x][j] && !merged[x + 1][j] && !merged[x][j]) {
                        map[x + 1][j] *= 2;
                        map[x][j] = 0;
                        merged[x + 1][j] = true;
                    }
                }
            }
        } else if (dir == 2) { // left
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (map[i][j] == 0) continue;
                    int y = j;
                    while (y > 0 && map[i][y - 1] == 0) {
                        map[i][y - 1] = map[i][y];
                        map[i][y] = 0;
                        y--;
                    }
                    if (y > 0 && map[i][y - 1] == map[i][y] && !merged[i][y - 1] && !merged[i][y]) {
                        map[i][y - 1] *= 2;
                        map[i][y] = 0;
                        merged[i][y - 1] = true;
                    }
                }
            }
        } else if (dir == 3) { // right
            for (int i = 0; i < N; i++) {
                for (int j = N - 2; j >= 0; j--) {
                    if (map[i][j] == 0) continue;
                    int y = j;
                    while (y < N - 1 && map[i][y + 1] == 0) {
                        map[i][y + 1] = map[i][y];
                        map[i][y] = 0;
                        y++;
                    }
                    if (y < N - 1 && map[i][y + 1] == map[i][y] && !merged[i][y + 1] && !merged[i][y]) {
                        map[i][y + 1] *= 2;
                        map[i][y] = 0;
                        merged[i][y + 1] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, N);
        }
        return newMap;
    }

    static int[][] copyMap(int[][] src) {
        int[][] newMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            newMap[i] = src[i].clone();
        }
        return newMap;
    }

    static void findMax(int[][] map) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }
}
