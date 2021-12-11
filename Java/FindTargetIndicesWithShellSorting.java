/*
You are given a 0-indexed integer array nums and a target element target.
A target index is an index i such that nums[i] == target.
Return a list of the target indices of nums after sorting nums in non-decreasing order. If there are no target indices, return an empty list.
The returned list must be sorted in increasing order.
*/

public class FindTargetIndicesWithShellSorting {
	public List<Integer> targetIndices(int[] nums, int target) {
		sort(nums);
		List<Integer> list = new ArrayList<>();
		int index = 0;

		while(index < nums.length && target >= nums[index]) {
			if(target == nums[index])
				list.add(index);
			index++;
		}

		return list;

	}


        //Using Shell sort
	private void sort(int[] nums) {
		int outer, inner;
		int temp;
		int h = 1;

		while(h < nums.length / 3) {
			h = h * 3 + 1;
		}

		while(h > 0) {
			for(outer = h; outer < nums.length; outer++) {
				temp = nums[outer];
				inner = outer;
				while(inner > h - 1 && nums[inner - h] >= temp) {
					nums[inner] = nums[inner - h];
					inner -= h; 
				}
				nums[inner] = temp;
			}
			h = (h - 1) / 3;
		}
	}
}	
