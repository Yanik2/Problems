/**
 *Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 */

public class MajorityElement {
	public int majorityElement(int[] nums) {
        final Map<Integer, Integer> map = new HashMap<>();
        final int target = nums.length / 2;

        for (int n : nums) {
            int counter = map.computeIfAbsent(n, k -> 0);
            if (++counter > target) {
                return n;
            }
            map.put(n, counter);
        }

        return -1;
    }
}
