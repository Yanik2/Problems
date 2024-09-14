class KthLargest {
    private int[] heap;
    private int size;

    public KthLargest(int k, int[] nums) {
        this.heap = new int[k];
        this.size = 0;

        for (int i = 0; i < k && i < nums.length; i++) {
            heap[i] = nums[i];
            size++;
        }
        heapify();

        for (int i = k; i < nums.length; i++) {
            if (heap[0] < nums[i]) {
                removeFirst();
                insert(nums[i]);
            }
        }

    }
    
    public int add(int val) {
        if (size < heap.length) {
            insert(val);
        } else {
            if (val > heap[0]) {
                removeFirst();
                insert(val);
            }
        }

        return heap[0];
    }

    private void removeFirst() {
        heap[0] = heap[--size];
        trickleDown(0);
    }

    private void insert(int value) {
        int lastIndex = size++;
        heap[lastIndex] = value;
        trickleUp(lastIndex);
    }

    private void trickleUp(int index) {
        int temp = heap[index];
        int parent = (index - 1) / 2; 

        while (index > 0 && temp < heap[parent]) {
            heap[index] = heap[parent];
            index = parent;
            parent = (index - 1) / 2;
        }

        heap[index] = temp;
    }

    private void heapify() {
        for (int i = (size - 1) / 2; i >= 0; i--) {
            trickleDown(i);
        }
    }

    private void trickleDown(int index) {
        int temp = heap[index];

        while(index < size / 2) {
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

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
