package dp_practice;

/*
最大连续子序列和
dp[i]表示以A[i]作为末尾的连续序列的最大和
    1.最大和的连续序列只有一个元素 --> 最大和为A[i]本身
    2.最大和的连续序列有多个元素   --> 最大和为dp[i]+A[i]
    状态转移方程: dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
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
