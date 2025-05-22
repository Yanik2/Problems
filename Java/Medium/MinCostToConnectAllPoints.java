/**
 *You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 */

public class MinCostToConnectAllPoints {
    public int minCostConnectPoints(int[][] points) {
        if (points.length <= 1) {
            return 0;
        }

        final Heap heap = new Heap(points.length * (points.length + 1) / 2);
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int weight = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                heap.add(i, j, weight);
            }
        }

        int[] graph = new int[points.length];
        int[] rank = new int[points.length];

        for (int i = 0; i < graph.length; i++) {
            graph[i] = i;
        }

        int summaryWeight = 0;
        for (int i = 0; i < graph.length - 1;) {
            final Heap.Node currentEdge = heap.pop();
            int rootI = find(graph, currentEdge.i);
            int rootJ = find(graph, currentEdge.j);
            if (rootI != rootJ) {
                union(graph, rank, rootI, rootJ);
                summaryWeight += currentEdge.weight;
                i++;
            }
        }

        return summaryWeight;
    }

    private int find(int[] graph, int x) {
        if (graph[x] == x) {
            return x;
        }

        return graph[x] = find(graph, graph[x]);
    }

    private void union(int[] graph, int[] rank, int i, int j) {
        if (rank[i] > rank[j]) {
            graph[j] = i;
        } else if (rank[i] < rank[j]) {
            graph[i] = j;
        } else {
            graph[j] = i;
            rank[i] += 1;
        }
    }

    class Heap {
        Node[] heap;
        int capacity;
        int size;

        void heapify() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                trickleDown(i);
            }
        }

        Node pop() {
            final Node value = heap[0];
            heap[0] = heap[size - 1];
            size--;
            trickleDown(0);
            return value;
        }

        void add(int i, int j, int weight) {
            final Node newNode = new Node();
            newNode.i = i;
            newNode.j = j;
            newNode.weight = weight;
            heap[size] = newNode;
            trickleUp(size);
            size++;
        }

        void trickleUp(int index) {
            Node tmp = heap[index];
            int parent = (index - 1) / 2;

            while (index > 0 && tmp.weight < heap[parent].weight) {
                heap[index] = heap[parent];
                index = parent;
                parent = (index - 1) / 2;
            }

            heap[index] = tmp;
        }

        void trickleDown(int index) {
            Node tmp = heap[index];

            while (index < size / 2) {
                int smallestChild = index * 2 + 1;

                if (smallestChild < size - 1 && heap[smallestChild].weight > heap[smallestChild + 1].weight) {
                    smallestChild++;
                }

                if (tmp.weight < heap[smallestChild].weight) {
                    break;
                } else {
                    heap[index] = heap[smallestChild];
                    index = smallestChild;
                }
            }

            heap[index] = tmp;
        }

        Heap(int capacity) {
            heap = new Node[capacity];
            this.capacity = capacity;
            this.size = 0;
        }

        class Node {
            int i;
            int j;
            int weight;
        }
    }
}
