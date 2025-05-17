/**
 *There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.
 */

public class FindIfPathExistsIfGraphStack {
    public boolean validPathStack(int n, int[][] edges, int source, int destination) {
        if (n == 1 || source == destination) {
            return true;
        }
        if (edges.length == 0) {
            return false;
        }

        final boolean[] visited = new boolean[n];
        final boolean[][] connections = new boolean[n][n];

        for (int i = 0; i < edges.length; i++) {
            final int[] edge = edges[i];
            connections[edge[0]][edge[1]] = true;
            connections[edge[1]][edge[0]] = true;
        }
        final var stack = new Stack();
        stack.add(source);

        return findPath(destination, connections, visited, stack);
    }

    private boolean findPath(int destination, boolean[][] connections, boolean[] visited, Stack stack) {
        while (!stack.isEmpty()) {
            int value = stack.pop();
            if (visited[value]) {
                continue;
            }

            visited[value] = true;
            if (value == destination) {
                return true;
            }

            for (int i = 0; i < connections.length; i++) {
                if (connections[value][i]) {
                    if (!visited[i])
                        stack.add(i);
                }
            }
        }

        return false;
    }

    class Stack {
        private Node first;
        private int size;

        int pop() {
            if (size == 0) return -1;
            final var node = first;
            first = first.next;
            size--;
            return node.value;
        }

        void add(int value) {
            final var newNode = new Node();
            newNode.value = value;
            newNode.next = first;
            first = newNode;
            size++;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            int value;
            Node next;
        }
    }
}
