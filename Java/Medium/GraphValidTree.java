/**
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.
 */

public class GraphValidTree {
	public boolean validTree(int n, int[][] edges) {
		if (edges.length != n - 1) return false;
		if (n == 1) return true;

		int[] root = new int[n];
		int[] rank = new int[n];

		for (int i = 0; i < n; i++) {
			root[i] = i;
		}

		for (int i = 0; i < n - 1; i++) {
			union(root, rank, edges[i][0], edges[i][1]);
		}

		int rootValue = find(root, root[0]);

		for (int i = 1; i < root.length; i++) {
			if (rootValue != find(root, root[i]) {
				return false;
			}
		}
		return true;
	}

	private int find(int[] root, int x) {
		if (root[x] == x) 
			return x;
		return root[x] = find(root, root[x]);
	}

	private void union(int[] root, int[] rank, int x, int y) {
		int rootX = find(root, x);
		int rootY = find(root, y);

		if (rootX != rootY) {
			if (rank[rootX] > rank[rootY])
				root[rootY] = rootX;
			else if (rank[rootX] < rank[rootY])
				root[rootX] = rootY;
			else {
				root[rootY] = rootX;
				rank[rootX] += 1;
			}
		}
	}
}
