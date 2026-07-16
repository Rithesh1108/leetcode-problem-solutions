import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {

        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) {
            red[e[0]].add(e[1]);
        }

        for (int[] e : blueEdges) {
            blue[e[0]].add(e[1]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        boolean[][] visited = new boolean[n][2];

        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0}); // last edge = red
        queue.offer(new int[]{0, 1}); // last edge = blue

        visited[0][0] = true;
        visited[0][1] = true;

        int distance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {

                int[] cur = queue.poll();
                int node = cur[0];
                int color = cur[1];

                if (ans[node] == -1) {
                    ans[node] = distance;
                }

                if (color == 0) { // previous edge was red, take blue
                    for (int next : blue[node]) {
                        if (!visited[next][1]) {
                            visited[next][1] = true;
                            queue.offer(new int[]{next, 1});
                        }
                    }
                } else { // previous edge was blue, take red
                    for (int next : red[node]) {
                        if (!visited[next][0]) {
                            visited[next][0] = true;
                            queue.offer(new int[]{next, 0});
                        }
                    }
                }
            }

            distance++;
        }

        return ans;
    }
}