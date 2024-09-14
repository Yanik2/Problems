public class KthLargestElementInArray2 {
	public int findKthElement(int[] nums, int k) {
		int[] heap = new int[k];

		for (int i = 0; i < k; i++) {
			heap[i] = nums[i];
		}

		heapify(heap);

		for (int i = k; i < nums.length; i++) {
			if (heap[0] < nums[i]) {
				pop(heap);
				insert(heap, nums[i]);
			}
		}

		return heap[0];
	}

	private heapify(int[] heap) {
		for (int i = (heap.length - 1) / 2; i >= 0; i--) {
			trickleDown(heap, i);
		}
	}

	private void trickleDown(int[] heap, int index) {
		int temp = heap[index];

		while(index < heap.length / 2) {
			int leftChild = index * 2 + 1;
			int smallerChild = leftChild;

			if (leftChild + 1 < heap.length && heap[leftChild] > heap[leftChild + 1]) {
				smallerChild++;
			}

			if (temp < heap[smallerChild]) {
				break;
			} else {
				heap[index] = heap[smallerChild];
				index = smallerChild;
			}
		}

		heap[index] = temp;
	}

	private void pop(heap) {
		heap[0] = heap[heap.length - 1];
		trickleDown(heap, 0);
	}

	private void insert(int[] heap, int value) {
		heap[heap.length - 1] = value;
		trickleUp(heap);
	}

	private void trickleUp(heap) {
		int index = heap.length - 1;
		int temp = heap[index];
		int parent = (index - 1) / 2; 

		while(index > 0 && temp < heap[parent]) {
			heap[index] = heap[parent];
			index = parent;
			parent = (index - 1) / 2;
		}

		heap[index] = temp;
	}
}










































