public class FindIfPathExistsInGraphBFS {
	public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (n == 1 || source == destination) return true;
        if (edges.length == 0) return false;

        final boolean[] visited = new boolean[n];
        final Map<Integer, List<Integer>> matrix = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            List<Integer> connections =
                matrix.computeIfAbsent(edges[i][0], e -> new ArrayList<>());
            connections.add(edges[i][1]);
            connections = matrix.computeIfAbsent(edges[i][1], e -> new ArrayList<>());
            connections.add(edges[i][0]);
        }
        final Queue queue = new Queue();
        queue.add(source);

        while (!queue.isEmpty()) {
            final var current = queue.poll();
            if (current == destination)
                return true;
            if (visited[current])
                continue;
            visited[current] = true;
            final List<Integer> connections = matrix.get(current);
            for (Integer i : connections) {
                queue.add(i);
            }
        }

        return false;
    }

    class Queue {
        Node last;
        Node first;
        int size = 0;

        void add(int x) {
            final Node newNode = new Node();
            newNode.value = x;
            if (size == 0) {
                first = newNode;
                last = newNode;
            } else {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                if (last.prev == null)
                    last.prev = newNode;
            }
            size++;
        }

        int poll() {
            final int value = last.value;
            last = last.prev;
            size--;
            return value;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            int value;
            Node next;
            Node prev;
        }
    }
}
