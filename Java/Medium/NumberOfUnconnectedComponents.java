/**
 * You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.

Return the number of connected components in the graph.
 */


public class NumberOfUnconnectedComponents {
	public int countComponents(int n, int[][] edges) {
     		if (n == 1) return 1;
        	if (n == 2) return n - edges.length;

	        int[] root = new int[n];
        	int[] rank = new int[n];

	        for (int i = 0; i < n; i++) {
        	    root[i] = i;
	        }
	
        	int counter = n;
	
        	for (int i = 0; i < edges.length; i++) {
	            int rootX = find(root, edges[i][0]);
        	    int rootY = find(root, edges[i][1]);
	
        	    if (rootX != rootY) {
                	if (rank[rootX] > rank[rootY]) {
                    		root[rootY] = rootX;
	                } else if (rank[rootX] < rank[rootY]) {
        	            root[rootX] = rootY;
                	} else {
	                    root[rootY] = rootX;
        	            rank[rootX] += 1;
                	}
	
	                counter--;
        	    }
	        }
	
        return counter;
    }

    private int find(int[] root, int x) {
        if (root[x] == x)
            return x;

        return root[x] = find(root, root[x]);
    }
}
