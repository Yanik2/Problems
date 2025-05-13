/**
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.
 */


public class NumberOfProvinces {
	public int findCircleNum(int[][] isConnected) {
		int roots = 0;

		for (int i = 0; i < isConnected.length; i++) {
			final var province = isConnected[i];
			boolean isRoot = false;

			for (int j = 0; j < province.length; j++) {
				if (province[j] == 1) {
					isRoot = true;
					cleanConnections(isConnected, j, i ,i);
				}
			}

			if (isRoot)
				roots++;
		}

		return roots;
	}

	private void cleanConnections(int[][] isConnected, int provindeIndex, int parent, int root) {
		for (int i = 0; i < isConnected[provinceIndex].length; i++) {
			if (isConnected[provinceIndex][i] == 1) {
				isConnected[provinceIndex][i] = 0;
				isConnected[i][provinceIndex] = 0;

				if (i != parent && i != provinceIndex && i != root) {
					cleanConnections(isConnected, i, provinceIndex, root);
			}
		}
	}
}
