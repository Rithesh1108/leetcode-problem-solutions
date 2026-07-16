class Solution {

    int[][] dir = {{-1,0},{1,0},{0,-1},{0,1}};

    public int islandPerimeter(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {
                    return dfs(grid, i, j);
                }
            }
        }

        return 0;
    }

    private int dfs(int[][] grid, int r, int c) {

        if (r < 0 || c < 0 || r >= grid.length || c >= grid[0].length)
            return 1;

        if (grid[r][c] == 0)
            return 1;

        if (grid[r][c] == -1)
            return 0;

        grid[r][c] = -1;

        int perimeter = 0;

        for (int[] d : dir) {
            perimeter += dfs(grid, r + d[0], c + d[1]);
        }

        return perimeter;
    }
}