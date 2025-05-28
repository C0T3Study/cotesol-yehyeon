class Solution {
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        visited = new boolean[n];
        
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                dfs(i, n, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    static void dfs(int comNum, int n, int[][] computers) {
        visited[comNum] = true;
        
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i] && computers[comNum][i] == 1)
                dfs(i, n, computers);
        }
        
    }
}
