import java.util.*;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            graph[time[0]].add(new int[]{time[1], time[2]});
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[k] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{k, 0});

        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int node = cur[0];
            int time = cur[1];

            if (time > dist[node])
                continue;

            for (int[] next : graph[node]) {

                int nei = next[0];
                int wt = next[1];

                if (dist[nei] > dist[node] + wt) {
                    dist[nei] = dist[node] + wt;
                    pq.offer(new int[]{nei, dist[nei]});
                }
            }
        }

        int ans = 0;

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            ans = Math.max(ans, dist[i]);
        }

        return ans;
    }
}