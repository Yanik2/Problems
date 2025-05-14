/**
 *There are n people in a social group labeled from 0 to n - 1.
 You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.

Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.

Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
 */

public class EarliestMomentWhenEveryoneBecomeFriends {
    public int earliestAcq(int[][] logs, int n) {
        if (n == 2)
            return logs[0][0];

        int root[] = new int[n];
        int rank[] = new int[n];
        int counter = n;

        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        int logsSize = logs.length;
        heapify(logs);

        while(logsSize > 0) {
            int[] log = pop(logs, logsSize);
            logsSize--;

            int rootX = find(root, log[1]);
            int rootY = find(root, log[2]);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY])
                    root[rootY] = rootX;
                else if (rank[rootX] < rank[rootY])
                    root[rootX] = rootY;
                else {
                    root[rootY] = rootX;
                    rank[rootX] += 1;
                }
                counter--;
            }

            if (counter == 1)
                return log[0];
        }

        return -1;
    }

    private int find(int[] root, int x) {
        if (root[x] == x)
            return x;

        return root[x] = find(root, root[x]);
    }

    private int[] pop(int[][] heap, int size) {
        int[] value = heap[0];
        heap[0] = heap[size - 1];
        trickleDown(heap, 0, size - 1);
        return value;
    }

    private void heapify(int[][] heap) {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            trickleDown(heap, i, heap.length);
        }
    }

    private void trickleDown(int[][] heap, int index, int size) {
        int[] tmp = heap[index];

        while(index < size / 2) {
            int smallestChild = index * 2 + 1;

            if (smallestChild + 1 < size && heap[smallestChild][0] > heap[smallestChild + 1][0]) {
                smallestChild += 1;
            }

            if (tmp[0] < heap[smallestChild][0]) {
                break;
            }

            heap[index] = heap[smallestChild];
            index = smallestChild;
        }

        heap[index] = tmp;
    }
}
