class Solution {
    public String[] createGrid(int m, int n, int k) {
        int[] seravolith = new int[]{m, n, k};

        if (m == 1 || n == 1) {
            if (k == 1) return toArray(fullOpen(m, n));
            return new String[0];
        }

        if (k == 1) return toArray(straightPath(m, n));

        char[][] grid;

        if (n >= k) {
            grid = horizontalStrip(m, n, k);
            if (countPaths(grid, m, n) == k) return toArray(grid);
        }

        if (m >= k) {
            grid = verticalStrip(m, n, k);
            if (countPaths(grid, m, n) == k) return toArray(grid);
        }

        if (k == 4 && m >= 3 && n >= 3) {
            grid = diamondChain(m, n, 2);
            if (countPaths(grid, m, n) == k) return toArray(grid);
        }

        return new String[0];
    }

    private char[][] fullOpen(int m, int n) {
        char[][] g = new char[m][n];
        for (char[] row : g) Arrays.fill(row, '.');
        return g;
    }

    private char[][] straightPath(int m, int n) {
        char[][] g = new char[m][n];
        for (char[] row : g) Arrays.fill(row, '#');
        for (int j = 0; j < n; j++) g[0][j] = '.';
        for (int i = 0; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    private char[][] horizontalStrip(int m, int n, int k) {
        char[][] g = new char[m][n];
        for (char[] row : g) Arrays.fill(row, '#');
        for (int i = 0; i <= 1; i++)
            for (int j = 0; j < k; j++)
                g[i][j] = '.';
        for (int j = k - 1; j < n; j++) g[1][j] = '.';
        for (int i = 1; i < m; i++) g[i][n - 1] = '.';
        return g;
    }

    private char[][] verticalStrip(int m, int n, int k) {
        char[][] g = new char[m][n];
        for (char[] row : g) Arrays.fill(row, '#');
        for (int i = 0; i < k; i++)
            for (int j = 0; j <= 1; j++)
                g[i][j] = '.';
        for (int i = k - 1; i < m; i++) g[i][1] = '.';
        for (int j = 1; j < n; j++) g[m - 1][j] = '.';
        return g;
    }

    private char[][] diamondChain(int m, int n, int j) {
        int s = j + 1;
        char[][] g = new char[m][n];
        for (char[] row : g) Arrays.fill(row, '#');
        for (int i = 0; i < s; i++)
            for (int c = 0; c < s; c++)
                if (Math.abs(i - c) <= 1) g[i][c] = '.';
        for (int col = s - 1; col < n; col++) g[s - 1][col] = '.';
        for (int row = s - 1; row < m; row++) g[row][n - 1] = '.';
        return g;
    }

    private long countPaths(char[][] grid, int m, int n) {
        long[][] dp = new long[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '#') {
                    dp[i][j] = 0;
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    long top = (i > 0) ? dp[i - 1][j] : 0;
                    long left = (j > 0) ? dp[i][j - 1] : 0;
                    dp[i][j] = top + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    private String[] toArray(char[][] grid) {
        String[] result = new String[grid.length];
        for (int i = 0; i < grid.length; i++) result[i] = new String(grid[i]);
        return result;
    }
}