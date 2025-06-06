/**
 *Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

    TwoSum() Initializes the TwoSum object, with an empty array initially.
    void add(int number) Adds number to the data structure.
    boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.

 */

public class TwoNumsIII {
	private List<Integer> nums;
    private Map<Integer, Integer> map;

    public TwoSum() {
        this.nums = new ArrayList<>();
        this.map = new HashMap<>();
    }

    public void add(int number) {
        this.nums.add(number);
        final int counter = map.computeIfAbsent(number, n -> 0);
        map.put(number, counter + 1);
    }

    public boolean find(int value) {
        for (Integer n : nums) {
            int diff = value - n;
            if (diff == n) {
                if (map.getOrDefault(diff, 0) > 1) {
                    return true;
                }
            } else {
                if (map.containsKey(diff))
                    return true;
            }
        }
        return false;
    }
}
