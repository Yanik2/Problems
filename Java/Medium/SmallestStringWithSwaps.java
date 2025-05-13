/**
 * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b] indicates 2 indices(0-indexed) of the string.

You can swap the characters at any pair of indices in the given pairs any number of times.

Return the lexicographically smallest string that s can be changed to after using the swaps.
*/

public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.isEmpty()) return s;

        final int[] root = new int[s.length()];
        final int[] rank = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            root[i] = i;
            rank[i] = 1;
        }

        final var sBytes = s.getBytes();

        for (int i = 0; i < pairs.size(); i++) {
            final var pair = pairs.get(i);
            union(root, rank, pair.getFirst(), pair.get(1));
        }

        for (int i = 0; i < root.length - 1; i++) {
            final var rootI = find(root, i);
            for (int j = i + 1; j < root.length; j++) {
                final var rootJ = find(root, j);
                if (rootI == rootJ) {
                   swap(sBytes, i, j);
                }
            }
        }

        return new String(sBytes);
    }

    private int find(int[] root, int x) {
        if (x == root[x])
            return x;

        final int rootValue = find(root, root[x]);
        root[x] = rootValue;
        return rootValue;
    }

    private void union(int[] root, int[] rank, int x, int y) {
        final var rootX = find(root, x);
        final var rootY = find(root, y);


        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                root[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                root[rootX] = rootY;
            } else {
                root[rootX] = rootY;
                rank[rootY] += 1;
            }
        }
    }

    private void swap(byte[] bytes, int x, int y) {
        if (bytes[x] > bytes[y]) {
            final var tmp = bytes[x];
            bytes[x] = bytes[y];
            bytes[y] = tmp;
        }
    }
}
