public class KthLargestElementInArray {
	public int findKthLargest(int[] nums, int k) {
		heapify(nums);

		for (int i = 0, size = nums.length; i < k - 1; i++, size--) {
			pop(nums, size);
		}

		return nums[0];
	}

	private void heapify(int[] nums) {
		for (int i = nums.length / 2 - 1; i >= 0; i--) {
			trickleDown(nums, i, nums.length);
		}
	}

	private int pop(int[] nums, int size) {
		int value = nums[0];
		nums[0] = nums[size - 1];
		trickleDown(nums, 0, size);
		return value;
	}

	private void trickleDown(int[] nums, int index, int size) {
		int temp = nums[index];

		while(index < size / 2) {
			int leftChildIndex = index * 2 + 1;
			int largestChildIndex = leftChildIndex;

			if (leftChildIndex + 1 < size && nums[leftChildIndex] < nums[leftChildIndex + 1])  {
				largestChildIndex++;
			}

			if (temp > nums[largestChildIndex]) {
				break;
			} else {
				nums[index] = nums[largestChildIndex];
				index = largestChildIndex;
			}
		}

		nums[index] = temp;
	}
}
