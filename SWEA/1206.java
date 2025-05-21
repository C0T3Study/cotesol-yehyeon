import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
 
public class Solution {
    static int T = 10;
    static int N;
    static int[] map;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        for(int testCase = 1 ; testCase <= T ; testCase++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0 ; i < N ; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }
             
            int cnt = 0;
            for(int i = 2; i < N-2 ; i++) {
                int cur = map[i];
                while(cur > 0) {
                    if(map[i-1] < cur && map[i-2] < cur
                            && map[i+1] < cur && map[i+2] < cur) { cnt++; cur--;}
                     
                    else break;
                }
                 
            }
             
            System.out.println("#" + testCase + " " + cnt);
             
        } // end of for(testCase)
 
    }
     
     
 
}
