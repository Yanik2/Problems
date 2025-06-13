/**
 *You are given an integer n, which indicates that there are n courses labeled from 1 to n. You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei.

In one semester, you can take any number of courses as long as you have taken all the prerequisites in the previous semester for the courses you are taking.

Return the minimum number of semesters needed to take all courses. If there is no way to take all the courses, return -1.
 */

public class ParallelCourses {
	public int minimumSemesters(int n, int[][] relations) {
        final boolean[] visited = new boolean[n + 1];
        final int[] steps = new int[n + 1];
        final Map<Integer, List<Integer>> adjMatrix = new HashMap<>();
        for (int i = 0; i < relations.length; i++) {
            final List<Integer> list =
                adjMatrix.computeIfAbsent(relations[i][0], r -> new ArrayList<>());
            list.add(relations[i][1]);
        }

        int maxPath = 0;
        for (int i = 1; i <= n; i++) {
            int pathLen = getPathLen(i, visited, steps, adjMatrix, new boolean[n + 1]);
            if (pathLen == -1) {
                return pathLen;
            }
            if (maxPath < pathLen) {
                maxPath = pathLen;
            }
        }

        return maxPath;
    }

    private int getPathLen(int currentValue, boolean[] visited, int[] steps, Map<Integer,
        List<Integer>> adjMatrix, boolean[] currentStack) {
        if (currentStack[currentValue]) {
            return -1;
        }
        currentStack[currentValue] = true;
        if (visited[currentValue]) {
            currentStack[currentValue] = false;
            return steps[currentValue];
        }
        visited[currentValue] = true;

        final var connections = adjMatrix.get(currentValue);
        if (connections == null) {
            steps[currentValue] = 1;
            currentStack[currentValue] = false;
            return steps[currentValue];
        }

        for (Integer connection : connections) {
            int pathLen = getPathLen(connection, visited, steps, adjMatrix, currentStack);
            if (pathLen == -1) {
                return -1;
            }
            if (steps[currentValue] <= pathLen) {
                steps[currentValue] = pathLen + 1;
            }

        }
        currentStack[currentValue] = false;
        return steps[currentValue];
    }
}

