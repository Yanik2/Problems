/**
 * You are given an inclusive range [lower, upper] and a sorted unique integer array nums, where all elements are within the inclusive range.

A number x is considered missing if x is in the range [lower, upper] and x is not in nums.

Return the shortest sorted list of ranges that exactly covers all the missing numbers. That is, no element of nums is included in any of the ranges, and each missing number is covered by one of the ranges.
*/

public class MissingRanges {
	public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {
        final List<List<Integer>> result = new ArrayList<>();

        for (int j = 0; j < nums.length; j++, lower++) {
            if (lower != nums[j]) {
                int lowerMissing = lower;
                int upperMissing = nums[j] - 1;
                lower = nums[j];
                result.add(List.of(lowerMissing, upperMissing));
            }
        }

        if (lower <= upper) {
            result.add(List.of(lower, upper));
        }

        return result;
    }
}
