/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 */

public class FindIfPathExistsInGraphDisjointSet {
	public boolean validPathDisjointSet(int n, int[][] edges, int source, int destination) {
        int[] root = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int i = 0; i < edges.length; i++) {
            union(root, rank, edges[i][0], edges[i][1]);
        }

        return find(root, source) == find(root, destination);
    }

    private int find(int[] root, int x) {
        if (x == root[x]) {
            return x;
        }

        return root[x] = find(root, root[x]);
    }

    private void union(int[] root, int[] rank, int x, int y) {
        int rootX = find(root, x);
        int rootY = find(root, y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootY] = rootX;
                rank[rootX] += 1;
            }
        }
    }
}
