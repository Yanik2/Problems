public class LastStoneWeight {
	public int lastStoneWeight(int[] stones) {
        	heapify(stones);

        	int size = stones.length;

        	while (size > 1) {
            	int x = pop(stones, size--);
            	int difference = x - stones[0];
            	int absDif = difference < 0 ? -difference : difference;

            	if (absDif == 0) {
                	pop(stones, size--);
            	} else {
                	stones[0] = absDif;
                	trickleDown(stones, 0, size);
            	}
        	}

        	return size == 0 ? size : stones[0];
    	}

    	private int pop(int[] nums, int size) {
        	int value = nums[0];
        	nums[0] = nums[size - 1];
        	nums[size - 1] = - 1;
        	trickleDown(nums, 0, size);
        	return value;
    	}


    	private void heapify(int[] nums) {
        	for (int i = (nums.length / 2) - 1; i >= 0; i--) {
            	trickleDown(nums, i, nums.length);
        	}
    	}

    	private void trickleDown(int[] nums, int index, int size) {
        	int temp = nums[index];

        	while (index < size / 2) {
            		int leftChild = index * 2 + 1;
            		int largestChild = leftChild;

            		if (largestChild + 1 < size && nums[leftChild] < nums[leftChild + 1]) {
                		largestChild++;
            		}

            		if (temp > nums[largestChild]) {
                		break;
            		} else {
                		nums[index] = nums[largestChild];
                		index = largestChild;
            		}
        	}
	
        	nums[index] = temp;
    	}
}
