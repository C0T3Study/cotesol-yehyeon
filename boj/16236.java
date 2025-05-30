
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int N, M = 0, x, y;
	static int[][] fish;
	static boolean[][] visited;
	static int eat = 0; // 지금까지 먹은 물고기
	static int shark = 2; // 상어의 크기
	static int cnt = 0;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		fish = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				fish[i][j] = Integer.parseInt(st.nextToken());
				
				if(fish[i][j] == 0) continue;
				
				if(fish[i][j] == 9) {
					x=i; y=j;
					fish[i][j] = 0;
					continue;
				} 
			}
		}

		while(solve()) {}
		
		System.out.print(cnt);
		
		
	}
	
	
	

	
	static boolean solve() {
		int time = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x,y,0));
		visited = new boolean[N][N];
		visited[x][y] = true;

		int minTime = Integer.MAX_VALUE;
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;

		while(!q.isEmpty()) {
			Node curr = q.poll();
			
			if(curr.t >= minTime) break;
			
			
			
			for(int i = 0 ; i < 4 ; i++) {
				int nx = curr.x + dx[i], ny = curr.y + dy[i];
				if(nx<0 || nx >= N || ny<0 || ny>=N) continue;
				if(visited[nx][ny]) continue;
				if(fish[nx][ny] > shark) continue;
				
				if(fish[nx][ny]>0 && fish[nx][ny]<shark) {
					if(nx<minX) {
						minX = nx; minY = ny; minTime = curr.t+1;
					} else if(nx==minX) {
						if(ny < minY) {
							minY = ny; minTime = curr.t+1;
						}
					}
				}
				
				q.add(new Node(nx, ny, curr.t+1));
				visited[nx][ny] = true;
				
			}
			
			
			
			
			
			
		}
		
		if(minX == Integer.MAX_VALUE) return false;
		else {
			x = minX; y = minY;
			
			eat++;
			if(eat == shark) {shark++; eat = 0;}
			
			time = minTime;
			cnt+= minTime;
			fish[x][y] = 0;
			
			return true;
		}
		
	}
	

	
}

class Node{
	int x, y, t;
	
	public Node(int x, int y, int t) {
		this.x = x; this.y = y; this.t = t;
	}
}
