/**
 *Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 */

public class AllPatshFromSourceToTargetBFS {
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        final int target = graph.length - 1;

        final List<List<Integer>> result = new ArrayList<>();
        final Queue queue = new Queue();
        final List<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);
        queue.add(initialPath);
        recursion(graph, result, target, queue);

        return result;
    }

    private void recursion(int[][] graph, List<List<Integer>> result, int destination,
                           Queue queue) {
        if (queue.isEmpty()) return;

        final List<Integer> path = queue.pop();
        final int lastElement = path.getLast();

        for (int i : graph[lastElement]) {
            final var newPath = new ArrayList<Integer>(path);
            newPath.add(i);
            if (i == destination) {
                result.add(new ArrayList<>(newPath));
            } else {
                queue.add(new ArrayList<>(newPath));
            }
        }

        recursion(graph, result, destination, queue);
    }

    class Queue {
        Node first;
        Node last;
        int size = 0;

        void add(List<Integer> value) {
            final Node newNode = new Node();
            newNode.value = value;

            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                if (last.prev == null) {
                    last.prev = newNode;
                }
            }

            size++;
        }

        List<Integer> pop() {
            if (size != 0) {
                final List<Integer> value = last.value;
                last = last.prev;
                size--;
                return value;
            }
            return List.of();
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            Node prev;
            Node next;
            List<Integer> value;
        }
    }
}

