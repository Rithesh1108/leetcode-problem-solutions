import java.util.*;

class Solution {

    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {

        List<long[]>[] graph = new ArrayList[n];
        List<long[]>[] reverse = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            reverse[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(new long[]{e[1], e[2]});
            reverse[e[1]].add(new long[]{e[0], e[2]});
        }

        long[] d1 = dijkstra(graph, src1, n);
        long[] d2 = dijkstra(graph, src2, n);
        long[] d3 = dijkstra(reverse, dest, n);

        long ans = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            if (d1[i] == Long.MAX_VALUE ||
                d2[i] == Long.MAX_VALUE ||
                d3[i] == Long.MAX_VALUE)
                continue;

            ans = Math.min(ans, d1[i] + d2[i] + d3[i]);
        }

        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    private long[] dijkstra(List<long[]>[] graph, int src, int n) {

        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));

        dist[src] = 0;
        pq.offer(new long[]{src, 0});

        while (!pq.isEmpty()) {

            long[] cur = pq.poll();
            int node = (int) cur[0];
            long cost = cur[1];

            if (cost > dist[node])
                continue;

            for (long[] next : graph[node]) {

                int nei = (int) next[0];
                long wt = next[1];

                if (dist[nei] > cost + wt) {
                    dist[nei] = cost + wt;
                    pq.offer(new long[]{nei, dist[nei]});
                }
            }
        }

        return dist;
    }
}