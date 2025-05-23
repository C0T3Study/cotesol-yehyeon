
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution
{
	static int T, N, L;
	static int[] score;
	static int[] calo;
	static int[][] dp;
 
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	
    	for(int tc = 1 ; tc <= T ; tc++) {
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken()); // 재료 개수
    		L = Integer.parseInt(st.nextToken()); // 칼로리
    		score = new int[N+1]; calo = new int[N+1];
    		for(int i = 1 ; i <= N ; i++) {
    			st = new StringTokenizer(br.readLine());
    			score[i] = Integer.parseInt(st.nextToken());
    			calo[i] = Integer.parseInt(st.nextToken());
    		}
    		dp = new int[N+1][L+1];
    		solve();
    		System.out.println("#" + tc + " " + dp[N][L]);
    		
    	}
    	
    	
    }
    
    static void solve() {
    	
    	for(int i = 1 ; i <= N ; i++) {
    		for(int j = 1 ; j <= L ; j++) {
    			if(calo[i] <= j) {
    				dp[i][j] = Math.max(dp[i-1][j], score[i] + dp[i-1][j-calo[i]]);
    			} else {
    				dp[i][j] = dp[i-1][j];
    			}
    		}
    	}
    	
    	
    	
    }
     
}
