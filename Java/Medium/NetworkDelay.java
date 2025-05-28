/**
 *You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 */

public class NetworkDelay {
    public int networkDelayTime(int[][] times, int n, int k) {
        final boolean[] visited = new boolean[n + 1];
        final Map<Integer, List<int[]>> adjMatrix = new HashMap<>();
        final Delay[] delays = new Delay[n + 1];

        for (int i = 0; i < times.length; i++) {
            final List<int[]> connections =
                adjMatrix.computeIfAbsent(times[i][0], c -> new ArrayList<>());
            connections.add(times[i]);
        }

        int counter = 0;
        final Delay source = new Delay(k, k, 0);
        final Heap heap = new Heap((n * (n + 1)) / 2);
        delays[k] = source;
        heap.add(source);
        Delay max = source;

        while (!heap.isEmpty()) {
            if (counter == n) {
                return max.delay;
            }

            Delay current = heap.pop();
            final List<int[]> connections = adjMatrix.get(current.current);
            if (visited[current.current] || connections == null) {
                if (!visited[current.current]) {
                    visited[current.current] = true;
                    counter++;
                }
                continue;
            }

            for (int[] connection : connections) {
                if (delays[connection[1]] == null) {
                    final Delay newDelay =
                        new Delay(connection[1], connection[0], current.delay + connection[2]);
                    delays[connection[1]] = newDelay;
                    heap.add(newDelay);

                } else {
                    if (delays[connection[1]].delay > current.delay + connection[2]) {
                        delays[connection[1]].delay = current.delay + connection[2];
                        delays[connection[1]].prev = current.current;
                        heap.add(delays[connection[1]]);
                    }
                }
                if (max.delay < delays[connection[1]].delay) {
                    max = delays[connection[1]];
                }
            }

            visited[current.current] = true;
            counter++;
        }

        return counter == n ? max.delay : -1;

    }

    class Heap {
        Delay[] heap;
        int size;

        Heap(int initialCapacity) {
            this.heap = new Delay[initialCapacity];
        }

        void add(Delay delay) {
            heap[size] = delay;
            trickleUp(size);
            size++;
        }

        void trickleUp(int index) {
            int parent = (index - 1) / 2;
            final Delay tmp = heap[index];

            while (index > 0 && tmp.delay < heap[parent].delay) {
                heap[index] = heap[parent];
                index = parent;
                parent = (index - 1) / 2;
            }

            heap[index] = tmp;
        }

        Delay pop() {
            final Delay value = heap[0];
            heap[0] = heap[size - 1];
            trickleDown(0);
            size--;
            return value;
        }

        void trickleDown(int index) {
            final Delay tmp = heap[index];

            while (index < size / 2) {
                int smallestChild = index * 2 + 1;

                if (smallestChild < size - 1 && heap[smallestChild].delay > heap[smallestChild + 1].delay) {
                    smallestChild++;
                }

                if (tmp.delay < heap[smallestChild].delay) {
                    break;
                } else {
                    heap[index] = heap[smallestChild];
                    index = smallestChild;
                }
            }

            heap[index] = tmp;
        }

        boolean isEmpty() {
            return size == 0;
        }

    }

    class Delay {
        int current;
        int prev;
        int delay;

        Delay(int current, int prev, int delay) {
            this.current = current;
            this.prev = prev;
            this.delay = delay;
        }
    }
}
