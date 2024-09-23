public class KClosestPointToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        double[][] pointsWithDistance = new double[points.length][3];

        for (int i = 0; i < points.length; i++) {
            pointsWithDistance[i] =
            new double[]{points[i][0], points[i][1], Math.sqrt((points[i][0] * points[i][0]) + (points[i][1] * points[i][1]))};
        }

        heapify(pointsWithDistance);
        int pointsSize = pointsWithDistance.length;
        int[][] result = new int[k][2];

        for (int i = 0; i < k; i++, pointsSize--) {
            result[i] = pop(pointsWithDistance, pointsSize);
        }

        return result;
    }

    private int[] pop(double[][] points, int size) {
        double[] point = points[0];
        points[0] = points[size - 1];
        trickleDown(points, 0, size - 1);
        return new int[]{(int)point[0], (int)point[1]};
    }

    private void heapify(double[][] points) {
        for (int i = (points.length - 1) / 2; i >= 0; i--) {
            trickleDown(points, i, points.length);
        }
    }

    private void trickleDown(double[][] heap, int index, int size) {
        double[] temp = heap[index];

        while (index < size / 2) {
            int leftChild = index * 2 + 1;
            int smallestChild = leftChild;

            if (leftChild + 1 < size && comparePoints(heap[leftChild], heap[leftChild + 1]) > 0) {
                smallestChild++;
            }

            if (comparePoints(temp, heap[smallestChild]) < 0) {
                break;
            } else {
                heap[index] = heap[smallestChild];
                index = smallestChild;
            }
        }

        heap[index] = temp;
    }

    private double comparePoints(double[] point1, double[] point2) {
        return point1[2] - point2[2];
    }
}
