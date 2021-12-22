/*
 * You have a set of integers s, which originally contains all the numbers from 1 to n.
 * Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set,
 * which results in repetition of one number and loss of another number.
 * You are given an integer array nums representing the data status of this set after the error.
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 */

public class SetMismatch {
	public int[] findErrorNums(int[] nums) {
		int[] ret = new int[2];
		int[] newarr = new int[nums.length + 1];

		for(int i = 0; i < nums.length; i++) {
			if(newarr[nums[i]] == nums[i])
				ret[0] = nums[i];
			else
				newarr[nums[i]] = nums[i];
		}

		int index = 1;

		while(index < newarr.length && newarr[index] != 0)
			index++;

		ret[1] = index;

		return ret;
	}

}
