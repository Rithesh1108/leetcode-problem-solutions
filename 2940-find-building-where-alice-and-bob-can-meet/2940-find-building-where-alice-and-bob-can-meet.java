class Solution {

    int[] tree;
    int[] heights;
    int n;

    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {

        this.heights = heights;
        n = heights.length;
        tree = new int[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int a = queries[i][0];
            int b = queries[i][1];

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            if (a == b) {
                ans[i] = a;
                continue;
            }

            if (heights[a] < heights[b]) {
                ans[i] = b;
                continue;
            }

            ans[i] = query(1, 0, n - 1, b + 1, n - 1, heights[a]);
        }

        return ans;
    }

    private void build(int node, int l, int r) {

        if (l == r) {
            tree[node] = heights[l];
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
    }

    private int query(int node, int l, int r, int ql, int qr, int target) {

        if (l > qr || r < ql || tree[node] <= target) {
            return -1;
        }

        if (l == r) {
            return l;
        }

        int mid = (l + r) / 2;

        int left = query(node * 2, l, mid, ql, qr, target);

        if (left != -1) {
            return left;
        }

        return query(node * 2 + 1, mid + 1, r, ql, qr, target);
    }
}