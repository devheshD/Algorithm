import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node {
	int node1, node2;
	
	public Node(int node1, int node2) {
		this.node1 = node1;
		this.node2 = node2;
	}
}

public class Main {
	static int N, M;
	static int[] parents, rank;
	static int[][] map;
	static List<Node> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		map = new int[N][N];
		list = new ArrayList<Node>();
		
		for (int row = 0; row < N; row++) {
			String[] input = br.readLine().split(" ");
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(input[col]);
			}
		}
		
		for (int row = 0; row < N - 1; row++) {
			for (int col = row; col < N; col++) {
				if (map[row][col] == 1) {
					list.add(new Node(row + 1, col + 1));
				}
			}
		}
		
		make_set();
		
		for (int i = 0; i < list.size(); i++) {
			int city1 = list.get(i).node1;
			int city2 = list.get(i).node2;
			
			if (find(city1) != find(city2)) {
				union(city1, city2);
			}
			
		}
		

		String[] input = br.readLine().split(" ");
		int start = parents[Integer.parseInt(input[0])];

		for (int i = 1; i <= N; i++) {
			System.out.print(parents[i] + " ");
		}

		for (int i = 0; i < input.length; i++) {
			int city = Integer.parseInt(input[i]);
			
			if (start != find(city)) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");
		
	}

	private static void union(int city1, int city2) {
		int node1 = find(city1);
		int node2 = find(city2);
		
		if (rank[node1] < rank[node2]) {
			parents[node1] = node2;
		} else if (rank[node1] > rank[node2]) {
			parents[node2] = node1;
		} else {
			parents[node1] = node2;
			rank[node2]++;
		}
		
	}

	private static int find(int node) {
		if (parents[node] == node) {
			return node;
		}
		
		return find(parents[node]);
	}

	private static void make_set() {
		parents = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parents[i] = i;
		}
		
		rank = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			rank[i] = 1;
		}
	}


}
