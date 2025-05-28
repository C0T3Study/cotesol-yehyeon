
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, x, y, d, g;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
  	static int[][] map;
  	static int cnt = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
		map = new int[101][101];
		StringTokenizer st;
        for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			makeCurve();
        }
		
		count();
		System.out.print(cnt);
		
        
    }
	
	static void makeCurve() {
		
		List<Integer> l = new ArrayList<>();
		l.add(d);
		
		for(int i = 1 ; i <= g ; i++) {
			for(int j = (int)Math.pow(2,i-1)-1 ; j >= 0 ; j--) {
				int nd = l.get(j) + 1;
				if(nd > 3) nd = 0;
				
				l.add(nd);
			}
		}
		
		map[y][x] = 1;
		for(int dir : l) {
			int nx = x + dx[dir];
			int ny = y + dy[dir];
		
			map[ny][nx] = 1;
			x = nx; y = ny;
		}
	}
	
	static void count() {
		for(int i = 0 ; i < 100 ; i++) {
			for(int j = 0 ; j < 100 ; j++) {
				if(map[i][j] == 1 && map[i+1][j] == 1
				  && map[i][j+1] == 1 && map[i+1][j+1] == 1) cnt++;
			}
		}
		
	}

}
