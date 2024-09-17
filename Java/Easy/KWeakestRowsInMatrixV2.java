public class KWeakestRowsInMatrixV2 {
	private RowData[] heap;
    private int size;

    public int[] kWeakestRows(int[][] mat, int k) {
        heap = new RowData[mat.length];
        size = mat.length;

        for (int i = 0; i < mat.length; i++) {
            int soldiers = 0;
            while (soldiers < mat[i].length && mat[i][soldiers] != 0) {
                soldiers++;
            }

            heap[i] = new RowData(soldiers, i);
        }

        heapify();

        final int[] result = new int[k];

        for (int i = 0; i < k; i++) {
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

    private void trickleDown(int index, int size) {
        final RowData temp = heap[index];

        while (index < size / 2) {
            final int leftChild = index * 2 + 1;
            int smallestChild = leftChild;

            if (leftChild + 1 < heap.length && compareRows(heap[leftChild], heap[leftChild + 1]) > 0) {
                smallestChild++;
            }

            if (compareRows(temp, heap[smallestChild]) < 0) {
                break;
            } else {
                heap[index] = heap[smallestChild];
                index = smallestChild;
            }
        }

        heap[index] = temp;
    }

    private int compareRows(RowData left, RowData right) {
        int soldiers = left.soldiers - right.soldiers;

        return soldiers == 0 ? left.index - right.index : soldiers;
    }
