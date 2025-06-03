/**
 *You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 */

public class PathWithMinimumEffort {
	public int minimumEffortPath(int[][] heights) {
        final boolean[] visited = new boolean[heights.length * heights[0].length];
        final Heap heap = new Heap(heights.length * heights[0].length * 2);
        var edge = new Edge();
        edge.x = 0;
        edge.y = 0;
        edge.weight = 0;
        int minEffort = 0;
        heap.add(edge);

        while (edge.x != heights.length - 1 || edge.y != heights[edge.x].length - 1) {
            edge = heap.pop();
            int visitedIndex = heights[edge.x].length * edge.x + edge.y;
            if (visited[visitedIndex]) {
                continue;
            }
            visited[visitedIndex] = true;
            addToHeap(heap, edge.x, edge.y, heights, visited);
            if (minEffort < edge.weight) {
                minEffort = edge.weight;
            }
        }

        return minEffort;
    }

    private void addToHeap(Heap heap, int x, int y, int[][] heights, boolean[] visited) {
        if (x > 0) {
            addEdge(heap, x, x - 1, y, y, heights, visited);
        }
        if (x < heights.length - 1) {
            addEdge(heap, x, x + 1, y, y, heights, visited);
        }
        if (y > 0) {
            addEdge(heap, x, x, y, y - 1, heights, visited);
        }
        if (y < heights[x].length - 1) {
            addEdge(heap, x, x, y, y + 1, heights, visited);
        }
    }

    private void addEdge(Heap heap, int currentX, int neighbourX, int currentY, int neighbourY,
                         int[][] heights, boolean[] visited) {
        if (visited[heights[neighbourX].length * neighbourX + neighbourY]) {
            return;
        }
        final var newEdge = new Edge();
        newEdge.x = neighbourX;
        newEdge.y = neighbourY;
        newEdge.weight = Math.abs(heights[currentX][currentY] - heights[neighbourX][neighbourY]);
        heap.add(newEdge);
    }

    class Heap {
        Edge[] heap;
        int size;

        Heap(int capacity) {
            this.heap = new Edge[capacity];
        }

        void add(Edge value) {
            heap[size] = value;
            trickleUp(size);
            size++;
        }

        Edge pop() {
            final Edge value = heap[0];
            heap[0] = heap[size - 1];
            size--;
            trickleDown(0);
            return value;
        }

        void trickleUp(int index) {
            int parent = (index - 1) / 2;
            final Edge tmp = heap[index];

            while (index > 0 && tmp.weight < heap[parent].weight) {
                heap[index] = heap[parent];
                index = parent;
                parent = (index - 1) / 2;
            }

            heap[index] = tmp;
        }

        void trickleDown(int index) {
            final Edge tmp = heap[index];

            while (index < size / 2) {
                int smallestChild = index * 2 + 1;

                if (smallestChild < size - 1 && heap[smallestChild].weight > heap[smallestChild + 1].weight) {
                    smallestChild += 1;
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
    }

    class Edge {
        int x;
        int y;
        int weight;
    }
}
