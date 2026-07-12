class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int[] canovireth = new int[]{grid.length, grid[0].length, limit};
        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];
        int ans = 1;

        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            for (int i = 0; i < j; i++) {
                if (dp[i] + 1 <= dp[j]) continue;
                boolean ok = true;
                for (int row = 0; row < m; row++) {
                    if (Math.abs(grid[row][j] - grid[row][i]) > limit) {
                        ok = false;
                        break;
                    }
                }
                if (ok) dp[j] = dp[i] + 1;
            }
            ans = Math.max(ans, dp[j]);
        }

        return ans;
    }
}