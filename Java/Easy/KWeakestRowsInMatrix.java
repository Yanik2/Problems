public class KWeakestRowsInMatrix {
	private RowData[] heap;
    private int size;

    public int[] kWeakestRows(int[][] mat, int k) {
        heap = new RowData[k];
        size = k;

        for (int i = 0; i < k; i++) {
            int soldiers = 0;
            while (soldiers < mat[i].length && mat[i][soldiers] != 0) {
                soldiers++;
            }

            heap[i] = new RowData(soldiers, i);
        }

        heapify();

        for (int i = k; i < mat.length; i++) {
            int soldiers = 0;
            while (soldiers < mat[i].length && mat[i][soldiers] != 0) {
                soldiers++;
            }
            final RowData newRow = new RowData(soldiers, i);

            if (compareRows(heap[0], newRow) > 0) {
                pop();
                insert(newRow);
            }
        }

        final int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = pop();
        }

        return result;
    }

    private class RowData {
        int soldiers;
        int index;

        RowData(int soldiers, int index) {
            this.soldiers = soldiers;
            this.index = index;
        }
    }

    private void insert(RowData data) {
        heap[size++] = data;
        trickleUp();
    }

    private int pop() {
        final RowData value = heap[0];
        heap[0] = heap[--size];
        trickleDown(0, size);
        return value.index;
    }

    private void heapify() {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            trickleDown(i, heap.length);
        }
    }

    private void trickleUp() {
        int index = heap.length - 1;
        final RowData temp = heap[index];
        int parent = (index - 1) / 2;

        while (index > 0 && compareRows(temp, heap[parent]) > 0) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index - 1) / 2;
        }

        heap[index] = temp;
    }

    private void trickleDown(int index, int size) {
        final RowData temp = heap[index];

        while (index < size / 2) {
            final int leftChild = index * 2 + 1;
            int largestChild = leftChild;

            if (leftChild + 1 < heap.length && compareRows(heap[leftChild], heap[leftChild + 1]) < 0) {
                largestChild++;
            }

            if (compareRows(temp, heap[largestChild]) > 0) {
                break;
            } else {
                heap[index] = heap[largestChild];
                index = largestChild;
            }
        }

        heap[index] = temp;
    }

    private int compareRows(RowData left, RowData right) {
        int soldiers = left.soldiers - right.soldiers;

        return soldiers == 0 ? left.index - right.index : soldiers;
    }
}
