class Solution {

    class DSU {
        int[] parent, rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);

            if (px == py) {
                return false;
            }

            if (rank[px] < rank[py]) {
                parent[px] = py;
            } else if (rank[px] > rank[py]) {
                parent[py] = px;
            } else {
                parent[py] = px;
                rank[px]++;
            }

            return true;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        int m = edges.length;
        int[][] newEdges = new int[m][4];

        for (int i = 0; i < m; i++) {
            newEdges[i][0] = edges[i][0];
            newEdges[i][1] = edges[i][1];
            newEdges[i][2] = edges[i][2];
            newEdges[i][3] = i;
        }

        Arrays.sort(newEdges, (a, b) -> a[2] - b[2]);

        int baseWeight = kruskal(n, newEdges, -1, -1);

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < m; i++) {

            if (kruskal(n, newEdges, i, -1) > baseWeight) {
                critical.add(newEdges[i][3]);
            } else if (kruskal(n, newEdges, -1, i) == baseWeight) {
                pseudo.add(newEdges[i][3]);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(critical);
        ans.add(pseudo);

        return ans;
    }

    private int kruskal(int n, int[][] edges, int skip, int pick) {

        DSU dsu = new DSU(n);

        int weight = 0;
        int count = 0;

        if (pick != -1) {
            dsu.union(edges[pick][0], edges[pick][1]);
            weight += edges[pick][2];
            count++;
        }

        for (int i = 0; i < edges.length; i++) {

            if (i == skip) {
                continue;
            }

            if (dsu.union(edges[i][0], edges[i][1])) {
                weight += edges[i][2];
                count++;
            }
        }

        if (count != n - 1) {
            return Integer.MAX_VALUE;
        }

        return weight;
    }
}