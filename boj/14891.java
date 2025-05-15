import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static String[] w;
    static int K;
    static int[][] s;    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        w = new String[4];
        for(int i = 0 ; i < 4 ; i++) {
            w[i] = br.readLine();
        }
        K = Integer.parseInt(br.readLine());
        s = new int[K][2];
        StringTokenizer st;
        for(int i = 0 ; i < K ; i++) {
            st = new StringTokenizer(br.readLine());
            s[i][0] = Integer.parseInt(st.nextToken()) - 1;
            s[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < K; i++) {
            int[] dir = new int[4]; // 각 톱니바퀴의 회전 방향 (0이면 회전 안 함)
            int wheelNum = s[i][0];
            int clockwise = s[i][1];
            dir[wheelNum] = clockwise;
        
            // 왼쪽으로 전파
            for (int j = wheelNum - 1; j >= 0; j--) {
                if (w[j].charAt(2) != w[j + 1].charAt(6)) {
                    dir[j] = -dir[j + 1];
                } else break;
            }
        
            // 오른쪽으로 전파
            for (int j = wheelNum + 1; j < 4; j++) {
                if (w[j - 1].charAt(2) != w[j].charAt(6)) {
                    dir[j] = -dir[j - 1];
                } else break;
            }
        
            // 실제 회전 수행
            for (int j = 0; j < 4; j++) {
                if (dir[j] != 0) {
                    spin(j, dir[j]);
                }
            }
        }

        System.out.print(point());
 
    }

    static void spin(int wheelNum, int clockwise) {
        if(clockwise == 1) {
            Character temp = w[wheelNum].charAt(7);
            w[wheelNum] = temp + w[wheelNum].substring(0, 7);
        } else {
            Character temp = w[wheelNum].charAt(0);
            w[wheelNum] = w[wheelNum].substring(1) + temp;
        }
    }

    static int point() {
        int res = 0;
        int s = 1;
        
        for(int i = 0 ; i < 4 ; i++) {
            if(w[i].charAt(0) == '1') res += s;
            s *= 2;
        }

        return res;
    }
}
