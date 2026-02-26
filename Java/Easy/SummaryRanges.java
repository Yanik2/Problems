/**
 *
 * You are given a sorted unique integer array nums.

A range [a,b] is the set of all integers from a to b (inclusive).

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:

    "a->b" if a != b
    "a" if a == b
*/

public class SummaryRanges {
	public List<String> summaryRanges(int[] nums) {
        int i = 0;
        final var result = new LinkedList<String>();

        while (i < nums.length) {
            final var sb = new StringBuilder();
            sb.append(nums[i]);

            int tmp = i;
            while(tmp < nums.length - 1 && nums[tmp] + 1 == nums[tmp + 1]) {
                tmp++;
            }
            if (tmp != i) {
                sb.append("->");
                sb.append(nums[tmp]);
                i = tmp + 1;
            } else {
                i++;
            }

            result.add(sb.toString());
        }

        return result;
    }
}
