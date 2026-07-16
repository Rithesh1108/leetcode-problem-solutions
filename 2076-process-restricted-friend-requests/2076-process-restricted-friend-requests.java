class Solution {

    int[] parent;

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        boolean[] ans = new boolean[requests.length];

        for (int i = 0; i < requests.length; i++) {

            int u = requests[i][0];
            int v = requests[i][1];

            int pu = find(u);
            int pv = find(v);

            boolean valid = true;

            if (pu != pv) {
                for (int[] r : restrictions) {
                    int a = find(r[0]);
                    int b = find(r[1]);

                    if ((a == pu && b == pv) || (a == pv && b == pu)) {
                        valid = false;
                        break;
                    }
                }
            }

            ans[i] = valid;

            if (valid) {
                union(pu, pv);
            }
        }

        return ans;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pb] = pa;
        }
    }
}