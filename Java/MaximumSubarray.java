class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int tmp;

        for(int i = 0; i < nums.length; i++) {
            tmp = 0;
            for(int j = i; j >= 0; j--) {
                tmp += nums[j];
                if(tmp > max) {
                    max = tmp;
                }
            }
        }
        return max;
    }
}
