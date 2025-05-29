/**
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.

You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 */

public class CheapestFlightsWithinKStops {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        final Integer[][] bellmanFordMatrix = new Integer[k + 2][n];
        bellmanFordMatrix[0][src] = 0;
        final Map<Integer, List<int[]>> adjMatrix = new HashMap<>();

        for (int i = 0; i < flights.length; i++) {
            final var list = adjMatrix.computeIfAbsent(flights[i][1], c -> new ArrayList<>());
            list.add(flights[i]);
        }


        for (int i = 1; i < k + 2; i++) {
            for (int j = 0; j < n; j++) {
                if (j == src) {
                    bellmanFordMatrix[i][j] = 0;
                    continue;
                }
                final List<int[]> nodeConnections = adjMatrix.get(j);
                if (nodeConnections == null) {
                    continue;
                }
                for (int[] connection : nodeConnections) {
                    final var pointer = bellmanFordMatrix[i - 1][connection[0]];
                    if (pointer != null && (bellmanFordMatrix[i][j] == null || bellmanFordMatrix[i][j] > connection[2] + pointer)) {
                        bellmanFordMatrix[i][j] = connection[2] + pointer;
                    }
                }
            }
        }

        return bellmanFordMatrix[k + 1][dst] != null ? bellmanFordMatrix[k + 1][dst] : -1;
    }
}
