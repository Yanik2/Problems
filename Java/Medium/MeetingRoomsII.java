public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        heapify(intervals);
        int[] heap = new int[intervals.length];
        int rooms = 0;
        int heapSize = 0;

        int intervalSize = intervals.length;
        while (intervalSize > 0) {
            int[] meetingToCompare = pop(intervals, intervalSize--);

            while (heapSize > 0 && heap[0] <= meetingToCompare[0]) {
                popHeap(heap, heapSize);
                heapSize--;
            }
            insertIntoHeap(heap, meetingToCompare[1], heapSize);
            heapSize++;

            if (rooms < heapSize) {
                rooms = heapSize;
            }
        }

        return rooms;
    }

    private void trickleDownHeap(int[] heap, int size) {
        int index = 0;
        int temp = heap[0];

        while (index < size / 2) {
            int leftChild = index * 2 + 1;
            int smallestChild = leftChild;

            if (leftChild + 1 < size && heap[leftChild] > heap[leftChild + 1]) {
                smallestChild++;
            }

            if (temp < heap[smallestChild]) {
                break;
            } else {
                heap[index] = heap[smallestChild];
                index = smallestChild;
            }
        }

        heap[index] = temp;
    }

    private void trickleUpHeap(int[] heap, int index) {
        int parent = (index - 1) / 2;
        int temp = heap[index];

        while (index > 0 && temp < heap[parent]) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index - 1) / 2;
        }

        heap[index] = temp;
    }

    private void insertIntoHeap(int[] heap, int value, int size) {
        heap[size] = value;
        trickleUpHeap(heap, size);
    }

    private void popHeap(int[] heap, int size) {
        heap[0] = heap[size - 1];
        trickleDownHeap(heap, size - 1);
    }

    private void heapify(int[][] intervals) {
        for (int i = (intervals.length - 1) / 2; i >= 0; i--) {
            trickleDown(intervals, i, intervals.length);
        }
    }

    private int[] pop(int[][] heap, int size) {
        int[] value = heap[0];
        heap[0] = heap[size - 1];
        trickleDown(heap, 0, size);
        return value;
    }

    private void trickleDown(int[][] intervals, int index, int size) {
        int[] temp = intervals[index];

        while (index < size / 2) {
            int leftChild = index * 2 + 1;
            int smallestChild = leftChild;

            if (leftChild + 1 < size
                && compareIntervals(intervals[leftChild], intervals[leftChild + 1]) > 0) {
                smallestChild++;
            }

            if (compareIntervals(temp, intervals[smallestChild]) < 0) {
                break;
            } else {
                intervals[index] = intervals[smallestChild];
                index = smallestChild;
            }
        }

        intervals[index] = temp;
    }

    private int compareIntervals(int[] left, int[] right) {
        int startDiff = left[0] - right[0];
        return startDiff == 0 ? right[1] - left[1] : startDiff;
    }
}
