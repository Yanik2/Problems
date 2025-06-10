/**
 *You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 */

public class EvaluateDivision {
	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        final Map<String, List<Edge>> adjMatrix = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            final List<String> equation = equations.get(i);
            final double value = values[i];
            final Edge sourceEdge = new Edge();
            sourceEdge.source = equation.getFirst();
            sourceEdge.target = equation.get(1);
            sourceEdge.operation = Operation.MULTIPLY;
            sourceEdge.weight = value;
            final List<Edge> list = adjMatrix.computeIfAbsent(sourceEdge.source, s -> new ArrayList<>());
            list.add(sourceEdge);
            final Edge targetEdge = new Edge();
            targetEdge.source = sourceEdge.target;
            targetEdge.target = sourceEdge.source;
            targetEdge.operation = Operation.DIVIDE;
            targetEdge.weight = sourceEdge.weight;
            final List<Edge> targetList = adjMatrix.computeIfAbsent(targetEdge.source, s -> new ArrayList<>());
            targetList.add(targetEdge);
        }

        final double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            if (i == 4) {
                System.out.println();
            }
            final Queue queue = new Queue();
            final Set<String> visited = new HashSet<>();
            final String source = queries.get(i).getFirst();
            final String target = queries.get(i).get(1);

            Edge edge = new Edge();
            edge.source = source;
            edge.target = source;
            edge.weight = 1;
            edge.operation = Operation.MULTIPLY;
            queue.add(edge);
            boolean isPathFound = false;

            while (!queue.isEmpty()) {
                Edge currentEdge = queue.poll();
                if (visited.contains(currentEdge.source)) {
                    continue;
                }
                visited.add(currentEdge.source);
                final List<Edge> edges = adjMatrix.get(currentEdge.source);
                if (edges == null) {
                    isPathFound = false;
                    break;
                }
                if (currentEdge.source.equals(target)) {
                    isPathFound = true;
                    result[i] = currentEdge.weight;
                    break;
                }


                for (Edge e : edges) {
                    if (visited.contains(e.target)) {
                        continue;
                    }
                    Edge processed = new Edge();
                    processed.operation = e.operation;
                    processed.source = e.target;
                    processed.weight = e.operation.apply(currentEdge.weight, e.weight);
                    queue.add(processed);
                }

            }

            if (!isPathFound) {
                result[i] = -1.0;
            }
        }

        return result;
    }

    class Queue {
        Node first;
        Node last;
        int size;

        void add(Edge edge) {
            final Node newNode = new Node();
            newNode.value = edge;

            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.prev = first;
                first.next = newNode;
                first = newNode;
                if (last.next == null) {
                    last.next = newNode;
                }
            }
            size++;
        }

        Edge poll() {
            final Edge value = last.value;
            last = last.next;
            size--;
            return value;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            Edge value;
            Node next;
            Node prev;
        }
    }

    class Edge {
        String source;
        String target;
        Operation operation;
        double weight;
    }

    enum Operation {
        DIVIDE((x, y) -> x / y),
        MULTIPLY((x, y) -> x * y);

        private final BiFunction<Double, Double, Double> function;

        Operation(BiFunction<Double, Double, Double> function) {
            this.function = function;
        }

        double apply(double x, double y) {
            return function.apply(x, y);
        }
    }
}
