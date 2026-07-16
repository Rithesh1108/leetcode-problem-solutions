import java.util.*;

class Solution {

    List<int[]>[] graph;
    int[] values;
    int[] visited;
    int maxTime;
    int ans = 0;

    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {

        this.values = values;
        this.maxTime = maxTime;

        int n = values.length;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
            graph[edge[1]].add(new int[]{edge[0], edge[2]});
        }

        visited = new int[n];
        visited[0] = 1;

        dfs(0, 0, values[0]);

        return ans;
    }

    private void dfs(int node, int time, int score) {

        if (node == 0) {
            ans = Math.max(ans, score);
        }

        for (int[] next : graph[node]) {

            int nei = next[0];
            int cost = next[1];

            if (time + cost > maxTime)
                continue;

            boolean firstVisit = visited[nei] == 0;

            visited[nei]++;

            dfs(nei, time + cost,
                    firstVisit ? score + values[nei] : score);

            visited[nei]--;
        }
    }
}