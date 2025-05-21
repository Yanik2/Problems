/**
 *You are given an m x n grid where each cell can have one of three values:

    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.

Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 */

public class RottingOranges {
	public int orangesRotting(int[][] grid) {
        if (grid.length == 1 && grid[0].length == 1) {
            if (grid[0][0] == 1) {
                return -1;
            } else {
                return 0;
            }
        }

        int max = 0;
        Queue queue = new Queue();
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    max++;
                    if (grid[i][j] == 1) {
                        if (visited[i][j])
                            continue;
                        if (checkHealthPath(grid, i, j, visited))
                            return -1;
                    } else if (grid[i][j] == 2) {
                        queue.add(new int[]{i, j});
                    }
                }

            }
        }

        if (max == 0 || queue.size == max) return 0;
        if (queue.size == 0) return -1;

        int queueSize = queue.size;
        int counter = 0;

        while (queue.size < max) {
            for (int i = 0; i < queueSize; i++) {
                int[] rotten = queue.pop();

                if (rotten[0] - 1 >= 0) {
                    addToQueue(grid, rotten[0] - 1, rotten[1], queue);
                }
                if (rotten[0] + 1 < grid.length) {
                    addToQueue(grid, rotten[0] + 1, rotten[1], queue);
                }
                if (rotten[1] - 1 >= 0) {
                    addToQueue(grid, rotten[0], rotten[1] - 1, queue);
                }
                if (rotten[1] + 1 < grid[rotten[0]].length) {
                    addToQueue(grid, rotten[0], rotten[1] + 1, queue);
                }
                queue.add(rotten);
            }
            queueSize = queue.size;
            counter++;
        }

        return counter;
    }

    private void addToQueue(int[][] grid, int x, int y, Queue queue) {
        if (grid[x][y] == 1) {
            grid[x][y] = 2;
            queue.add(new int[]{x, y});
        }
    }

    private boolean checkForNeighbors(int[][] grid, int i, int j) {
        return (i - 1 >= 0 && grid[i - 1][j] != 0)
            || (i + 1 < grid.length && grid[i + 1][j] != 0)
            || (j - 1 >= 0 && grid[i][j - 1] != 0)
            || (j + 1 < grid[i].length && grid[i][j + 1] != 0);
    }

    private boolean checkHealthPath(int[][] grid, int i, int j, boolean[][] visited) {
        final Queue queue = new Queue();
        queue.add(new int[]{i, j});

        boolean result = true;
        while (!queue.isEmpty()) {
            int[] current = queue.pop();
            if (visited[current[0]][current[1]])
                continue;

            if (current[0] - 1 >= 0) {
                int connection = grid[current[0] - 1][current[1]];
                if (connection != 0) {
                    if (connection == 2)
                        result = false;
                    else
                        queue.add(new int[]{current[0] - 1, current[1]});
                }
            }
            if (current[0] + 1 < grid.length) {
                int connection = grid[current[0] + 1][current[1]];
                if (connection != 0) {
                    if (connection == 2)
                        result = false;
                    else
                        queue.add(new int[]{current[0] + 1, current[1]});
                }
            }
            if (current[1] - 1 >= 0) {
                int connection = grid[current[0]][current[1] - 1];
                if (connection != 0) {
                    if (connection == 2)
                        result = false;
                    else
                        queue.add(new int[]{current[0], current[1] - 1});
                }
            }
            if (current[1] + 1 < grid[current[0]].length) {
                int connection = grid[current[0]][current[1] + 1];
                if (connection != 0) {
                    if (connection == 2)
                        result = false;
                    else
                        queue.add(new int[]{current[0], current[1] + 1});
                }
            }

            visited[current[0]][current[1]] = true;
        }

        return result;
    }

    class Queue {
        Node first;
        Node last;
        int size;

        void add(int[] val) {
            final Node newNode = new Node();
            newNode.val = val;

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

        int[] pop() {
            final int[] val = last.val;
            last = last.prev;
            size--;
            return val;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            int[] val;
            Node prev;
            Node next;
        }
    }
}
