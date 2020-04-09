package dp_practice;

public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] * nums[i], nums[i]);
        }
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (dp[i] > dp[k]) {
                k = i;
            }
        }
        return dp[k];
    }
}
