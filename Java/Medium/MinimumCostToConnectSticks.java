public class MinimumCostToConnectSticks {
    public int connectSticks(int[] sticks) {
        heapify(sticks);
        int size = sticks.length;

        int price = 0;
        while (size > 1) {
            int first = pop(sticks, size--);
            int second = pop(sticks, size--);
            int result = first + second;
            price += result;
            insert(sticks, size++, result);
        }

        return price;
    }

    private void heapify(int[] heap) {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            trickleDown(heap, i, heap.length);
        }
    }

    private int pop(int[] heap, int size) {
        int value = heap[0];
        heap[0] = heap[size - 1];
        trickleDown(heap, 0, size - 1);
        return value;
    }

    private void insert(int[] heap, int size, int value) {
        heap[size] = value;
        trickleUp(heap, size);
    }

    private void trickleUp(int[] heap, int index) {
        int temp = heap[index];
        int parent = (index - 1) / 2;

        while (index > 0 && heap[parent] > temp) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index - 1) / 2;
        }

        heap[index] = temp;
    }

    private void trickleDown(int[] heap, int index, int size) {
        int temp = heap[index];

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
}
