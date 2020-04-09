package dp_practice;

/*
最长不下降子序列LIS(Longest Increasing Swquence)
dp[i]表示以A[i]结尾的LIS长度
    1.如果存在A[i]之前的元A[j](j<i), 使得A[j]≤A[i]且dp[j]+1>dp[i], 那么就把
      A[i]跟在以A[j]结尾的LIS后面,形成一条更长的LIS
    2.如果A[i]之前的元素逗比A[i]大, 那么A[i]就只好自己形成一条LIS, 但是长度为1
    状态转移方程: dp[i] = max{1. dp[j]+1}
 */
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int ans = 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
