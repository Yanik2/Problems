/**
 *Given an array of integers nums, half of the integers in nums are odd, and the other half are even.

Sort the array so that whenever nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.

Return any answer array that satisfies this condition.
 */

public class SortArrayByParityII {
	public int[] sortArrayByParityII(int[] nums) {
        int oddIndex = 1;
        int evenIndex = 0;
        int tmp;

        for (int i = 0, j = 1; i < nums.length && j < nums.length; i += 2, j += 2) {
            if (nums[i] % 2 != 0) {
                while (nums[oddIndex] % 2 == 1) {
                    oddIndex += 2;
                }
                
                tmp = nums[i];
                nums[i] = nums[oddIndex];
                nums[oddIndex] = tmp;
            }
            
            if (nums[j] % 2 != 1) {
                while (nums[evenIndex] % 2 == 0) {
                    evenIndex += 2;
                }
                
                tmp = nums[j];
                nums[j] = nums[evenIndex];
                nums[evenIndex] = tmp;
            }
        }

        return nums;
    }
}
