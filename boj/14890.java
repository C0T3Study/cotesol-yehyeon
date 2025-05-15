import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N, L;
    static int[][] map;
    static int[][] road;
    static boolean[][] isPut;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        road = new int[N+N][N];
        isPut = new boolean[N+N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                road[i][j] = map[i][j];
            }
        }
        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                road[i+N][j] = map[j][i];
            }
        }

        int cnt = 0;
        for(int i = 0 ; i < N+N ; i++) {
            if(isRoad(i) == true) {
                cnt++;
            }
        }
        
        System.out.print(cnt);
        
    }

    static boolean isRoad(int r) {
        // 정방향 탐색
        int e = 0;
        boolean isBuilding = false;
        
        for(int i = 1 ; i < N ; i++) {
            if(road[r][i-1] - road[r][i] > 1) { // 단차가 1보다 큰 경우
                return false;
            }

            if(road[r][i-1] - road[r][i] == 1) { // 단차가 1인 경우
                if(isBuilding) { // 이미 경사로를 짓고 있는 경우
                    isBuilding = false;
                    return false;
                }
                isBuilding = true;
                
                if(isPut[r][i]) return false;
                
                isPut[r][i] = true;
                e = i + L - 1; // 경사로가 끝나는 지점
                if(i == e) isBuilding = false; // L=1인 경우 대비
            }

            if(road[r][i-1] - road[r][i] == 0) {
                if(isBuilding) {
                    if(isPut[r][i]) return false; // 이미 경사로가 놓인 경우

                    isPut[r][i] = true;
                    if(i == e) isBuilding = false;
                }
            }
        }
        
        if(isBuilding) return false; // 경사로를 다 짓지 못했으면 길이 아님
        
        isBuilding = false; e = 0; // 초기화
        // 역방향 탐색
        for(int i = N-2 ; i >= 0 ; i--) {
            if(road[r][i+1] - road[r][i] > 1) { // 단차가 1보다 큰 경우
                return false;
            }

            if(road[r][i+1] - road[r][i] == 1) { // 단차가 1인 경우
                if(isBuilding) { // 이미 경사로를 짓고 있는 경우
                    isBuilding = false;
                    return false;
                }
                isBuilding = true;
                
                if(isPut[r][i]) return false;
                
                isPut[r][i] = true;
                e = i - L + 1; // 경사로가 끝나는 지점
                if(i == e) isBuilding = false; // L=1인 경우 대비
            }

            if(road[r][i+1] - road[r][i] == 0) {
                if(isBuilding) {
                    if(isPut[r][i]) return false; // 이미 경사로가 놓인 경우

                    isPut[r][i] = true;
                    if(i == e) isBuilding = false;
                }
            }
        }

        if(isBuilding) return false;
        return true;
    }
}
