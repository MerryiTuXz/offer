package dp_practice;

public class UniquePaths {
    //    public int uniquePaths(int m, int n) {
//        if (m == 1)
//            return 1;
//        if (n == 1)
//            return 1;
//        return uniquePaths(m-1, n) + uniquePaths(m, n-1);
//    }
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                dp[j][i] = dp[j - 1][i] + dp[j][i - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
