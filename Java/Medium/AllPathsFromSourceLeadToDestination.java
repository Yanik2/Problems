/**
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:

    At least one path exists from the source node to the destination node
    If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
    The number of possible paths from source to destination is a finite number.

Return true if and only if all roads from source lead to destination.
 *
 */

public class AllPathsFromSourceLeadToDestination {
	public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        if (n == 1) return edges.length == 0;
        if (edges.length == 0) return false;
        if (source == destination) return false;

        final boolean[] visited = new boolean[n];
        final boolean[] connected = new boolean[n];
        final boolean[][] matrix = new boolean[n][n];
        final boolean[] valid = new boolean[n];

        for (int i = 0; i < edges.length; i++) {
            matrix[edges[i][0]][edges[i][1]] = true;
            connected[edges[i][0]] = true;
        }

        if (connected[destination])
            return false;

        return recursion(source, destination, visited, matrix, connected, valid) && visited[destination];

    }

    private boolean recursion(int source, int destination, boolean[] visited, boolean[][] matrix,
                           boolean[] connected, boolean[] valid) {
        if (valid[source]) {
            return true;
        }
        if (source == destination) {
            visited[destination] = true;
            return true;
        }
        if (visited[source])
            return false;
        if (!connected[source])
            return false;

        visited[source] = true;
        for (int i = 0; i < visited.length; i++) {
            if (matrix[source][i]) {
                if (recursion(i, destination, visited, matrix, connected, valid)) {
                    valid[i] = true;
                } else {
                    return false;
                }
            }
        }

        visited[source] = false;
        return true;
    }
}
