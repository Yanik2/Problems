/**
 *There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

    For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.

Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
 */

public class CourseScheduleII {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        final boolean[] visited = new boolean[numCourses];
        final int[] courses = new int[numCourses];
        final Map<Integer, List<Integer>> adjMatrix = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            final List<Integer> list =
                adjMatrix.computeIfAbsent(prerequisites[i][1], k -> new ArrayList<>());
            list.add(prerequisites[i][0]);
            courses[prerequisites[i][0]] += 1;
        }

        final Queue queue = new Queue();
        for (int i = 0; i < courses.length; i++) {
            if (courses[i] == 0) {
                queue.add(i);
                visited[i] = true;
            }
        }

        final int[] result = new int[numCourses];

        for (int i = 0; i < result.length; i++) {
            if (queue.isEmpty()) {
                return new int[0];
            }
            final int currentValue = queue.poll();
            result[i] = currentValue;
            final List<Integer> connections = adjMatrix.get(currentValue);

            if (connections == null) {
                continue;
            }
            for(Integer connection : connections) {
                if (!visited[connection]) {
                    courses[connection] -= 1;
                    if (courses[connection] == 0) {
                        queue.add(connection);
                        visited[connection] = true;
                    }
                }
            }
        }

        return result;
    }

    class Queue {
        Node first;
        Node last;
        int size;

        void add(int x) {
            final Node newNode = new Node();
            newNode.value = x;

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
            Node next;
            Node prev;
            int value;
        }
    }
}
