/**
 *Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */

public class AllPathsFromSourceToTarget {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        boolean[][] matrix = new boolean[graph.length][graph.length];

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                matrix[i][graph[i][j]] = true;
            }
        }

        final var stack = new Stack();
        final var target = graph.length - 1;
        final var result = new ArrayList<List<Integer>>();
        stack.add(0);

        recursion(result, target, 0, stack, matrix);
        return result;
    }

    private void recursion(
        List<List<Integer>> result,
        int target,
        int current,
        Stack stack,
        boolean[][] matrix
    ) {
        if (current == target) {
            result.add(stack.snapshot());
            stack.pop();
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            if (matrix[current][i]) {
                stack.add(i);
                recursion(result, target, i, stack, matrix);
            }
        }

        stack.pop();
    }

    static class Stack {
        private Node first;
        private int size;

        int pop() {
            if (size == 0) {
                return -1;
            }
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

        List<Integer> snapshot() {
            final var list = new LinkedList<Integer>();
            Node root = first;
            while (root != null) {
                list.addFirst(root.value);
                root = root.next;
            }

            return list;
        }

        static class Node {
            int value;
            Node next;
        }
    }
}
