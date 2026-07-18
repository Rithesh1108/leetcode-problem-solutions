class Solution {
    static final int DRAW = 0;
    static final int MOUSE = 1;
    static final int CAT = 2;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] color = new int[n][n][3];
        int[][][] degree = new int[n][n][3];
        Queue<int[]> queue = new LinkedList<>();

        for (int m = 0; m < n; m++) {
            for (int c = 0; c < n; c++) {
                degree[m][c][1] = graph[m].length;
                degree[m][c][2] = graph[c].length;
                for (int x : graph[c]) {
                    if (x == 0) {
                        degree[m][c][2]--;
                    }
                }
            }
        }

        for (int c = 1; c < n; c++) {
            color[0][c][1] = MOUSE;
            color[0][c][2] = MOUSE;
            queue.offer(new int[]{0, c, 1, MOUSE});
            queue.offer(new int[]{0, c, 2, MOUSE});
        }

        for (int m = 1; m < n; m++) {
            color[m][m][1] = CAT;
            color[m][m][2] = CAT;
            queue.offer(new int[]{m, m, 1, CAT});
            queue.offer(new int[]{m, m, 2, CAT});
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0], c = cur[1], turn = cur[2], result = cur[3];

            if (turn == 2) {
                for (int pm : graph[m]) {
                    if (color[pm][c][1] != DRAW) continue;

                    if (result == MOUSE) {
                        color[pm][c][1] = MOUSE;
                        queue.offer(new int[]{pm, c, 1, MOUSE});
                    } else {
                        degree[pm][c][1]--;
                        if (degree[pm][c][1] == 0) {
                            color[pm][c][1] = CAT;
                            queue.offer(new int[]{pm, c, 1, CAT});
                        }
                    }
                }
            } else {
                for (int pc : graph[c]) {
                    if (pc == 0) continue;
                    if (color[m][pc][2] != DRAW) continue;

                    if (result == CAT) {
                        color[m][pc][2] = CAT;
                        queue.offer(new int[]{m, pc, 2, CAT});
                    } else {
                        degree[m][pc][2]--;
                        if (degree[m][pc][2] == 0) {
                            color[m][pc][2] = MOUSE;
                            queue.offer(new int[]{m, pc, 2, MOUSE});
                        }
                    }
                }
            }
        }

        return color[1][2][1];
    }
}