import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] map;
    static int[][] divMap;
    static int[] popu = new int[5];
    static int tot = 0;
    static int ans = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        StringTokenizer st;
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tot += map[i][j];
            }
        }
        xy();
        System.out.print(ans);

        
    }

    // 기준점 정하기
    static void xy() {
        for(int i = 1 ; i <= N-2 ; i++) {
            for(int j = 2 ; j <= N-1 ; j++) {
                div(i,j);
            }
        }
        
    }

    // 경계
    static void div(int x, int y) {
        
        for(int d1 = 1 ; d1 <= y-1 ; d1++) {
            int maxd2 = Math.min(N-x-d1, N-y);
            for(int d2 = 1 ; d2 <= maxd2; d2++) {
                area(x, y, d1, d2);
            }
        }

        
    }

    static void area(int x, int y, int d1, int d2) {
        divMap = new int[N+1][N+1];

        for(int i = 0 ; i <= d1; i++) {
            divMap[x+i][y-i] = 5;
            divMap[x+d2+i][y+d2-i] = 5;
        }
        for(int i = 0 ; i <= d2 ; i++) {
            divMap[x+i][y+i] = 5;
            divMap[x+d1+i][y-d1+i] = 5;
        }

        int[] popu = new int[5];
        for(int r = 1 ; r < x+d1 ; r++) {
            for(int c = 1 ; c <= y ; c++) {
                if(divMap[r][c] == 5) break;
                popu[0] += map[r][c];
            }
        }
        for(int r = 1 ; r <= x+d2 ; r++) {
            for(int c = N ; c >=y+1 ; c--) {
                if(divMap[r][c] == 5) break;
                popu[1] += map[r][c];
            }
        }
        for(int r = x+d1 ; r <= N ; r++) {
            for(int c = 1 ; c < y+d2-d1 ; c++) {
                if(divMap[r][c] == 5) break;
                popu[2] += map[r][c];
            }
        }
        for(int r = x+d2+1 ; r <= N ; r++) {
            for(int c = N ; c >= y+d2-d1 ; c--) {
                if(divMap[r][c] == 5) break;
                popu[3] += map[r][c];
            }
        }

        popu[4] = tot-(popu[0]+popu[1]+popu[2]+popu[3]);
        Arrays.sort(popu);
        ans = Math.min(ans, popu[4]-popu[0]);
        
    }

    
    
}
