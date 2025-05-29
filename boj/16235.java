package sol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Tree{
	int r, c, age;
	boolean dead;
	
	public Tree(int r, int c, int age) {
		this.r = r; this.c = c; this.age = age;
		this.dead = false;
	}
}

class Main {
	static int N, M, K;
	static int[][] A;
	static int[][] nutMap;
	static ArrayList<Tree> tree = new ArrayList<>();
	static Deque<Integer> deadTree = new LinkedList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N][N];
		nutMap = new int[N][N];

		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < N ; j++) {
				A[i][j] = Integer.parseInt(st.nextToken());
				nutMap[i][j] = 5;
			}
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			tree.add(new Tree(x-1, y-1, z));
		}
		
		Collections.sort(tree, (t1, t2) -> t1.age - t2.age);
		
		for(int i = 0 ; i < K ; i++) {
			spring();
			summer();
			fall();
			winter();
		}
		
		System.out.print(tree.size());		
	}
    
    static void spring() {
    	for(int i = 0 ; i < tree.size() ; i++) {
    		Tree t = tree.get(i);
    		if(nutMap[t.r][t.c] < t.age) {
    			deadTree.add(i);
    			continue;
    		}
    		nutMap[t.r][t.c] -= t.age;
    		t.age++;
    	}
    }
    
    static void summer() {
    	while(!deadTree.isEmpty()) {
    		int index = deadTree.pollLast();
    		Tree t = tree.get(index);
    		nutMap[t.r][t.c] += t.age/2;
    		t.dead = true;
    	}
    }
    
    static void fall() {
    	int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    	int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    	
    	ArrayList<Tree> newTree = new ArrayList<Tree>();
    	
    	for(int i = 0 ; i < tree.size() ; i++) {
    		Tree t = tree.get(i);
    		if(t.age % 5 == 0 && !t.dead) {
    			for(int dir = 0 ; dir < 8 ; dir++) {
    				int nr = t.r + dr[dir];
    				int nc = t.c + dc[dir];
    				if(nr>=0 && nr<N && nc>=0 && nc<N) {
    					newTree.add(new Tree(nr, nc, 1));
    				}
    			}
    			
    		}
    		
    	}
    	
    	for(Tree t : tree) {
    		if(!t.dead) newTree.add(t);
    	}
    	tree = newTree;
    	
    	
    }
    
    static void winter() {
    	for(int i = 0 ; i < N ; i++) {
    		for(int j = 0 ; j < N ; j++) {
    			nutMap[i][j] += A[i][j];
    		}
    	}
    }
}
