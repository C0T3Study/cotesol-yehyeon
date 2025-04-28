import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    static int N;
    static int[][] p;
    static List<Integer> s = new LinkedList<>();
    static List<Integer> l = new LinkedList<>();
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        p = new int[N+1][N+1];
        StringTokenizer st;
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j <= N ; j++) {
                p[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        makeTeam(1, 0);
        System.out.println(min);
        
        
    }

    static void makeTeam(int start, int d) {
        if (d == N/2) {
            difPower();
            return;
        }
        for(int i = start ; i <= N ; i++) {
            s.add(start); 
            makeTeam(i+1, d+1);
            s.remove(s.size()-1); 
        }
        
    }

    static void difPower() {
        int powerS = 0;
        int powerL = 0;

        for(int i = 1 ; i <= N ; i++) {
            if(!s.contains(i)) l.add(i);
        }
        
        for(int i : s) {
            for(int j : s) {
                powerS += p[i][j];
            }
        }

        for(int i : l) {
            for(int j : l) {
                powerL += p[i][j];
            }
        }
        

        min = Math.min(min, Math.abs(powerS - powerL));

        l.clear();
        
    }

    
}
