public class KthSmallestElementInSortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
     	  	 final int[] heap = new int[matrix.length * matrix[0].length];

        	for (int i = 0, j = 0; i < matrix.length; i++) {
           		 for (int n = 0; n < matrix[i].length; n++, j++) {
               			heap[j] = matrix[i][n];
            		}
        	}	

        	heapify(heap);


        	int size = heap.length;
        	for (int i = 0; i < k - 1; i++) {
            	pop(heap, size--);
        	}

        return pop(heap, size);
    }

    private int pop(int[] heap, int size) {
        int value = heap[0];
        heap[0] = heap[size - 1];
        trickleDown(heap, 0, size);
        return value;
    }

    private void heapify(int[] heap) {
        for (int i = (heap.length - 1) / 2; i >= 0; i--) {
            trickleDown(heap, i, heap.length);
        }
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
