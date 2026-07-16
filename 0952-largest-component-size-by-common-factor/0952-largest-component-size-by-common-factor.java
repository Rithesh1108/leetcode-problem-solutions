import java.util.*;

class Solution {

    int[] parent;
    int[] size;

    public int largestComponentSize(int[] nums) {

        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        parent = new int[max + 1];
        size = new int[max + 1];

        for (int i = 0; i <= max; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        for (int num : nums) {

            for (int f = 2; f * f <= num; f++) {

                if (num % f == 0) {
                    union(num, f);
                    union(num, num / f);
                }
            }
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int root = find(num);
            map.put(root, map.getOrDefault(root, 0) + 1);
            ans = Math.max(ans, map.get(root));
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

        if (pa == pb) {
            return;
        }

        if (size[pa] < size[pb]) {
            parent[pa] = pb;
            size[pb] += size[pa];
        } else {
            parent[pb] = pa;
            size[pa] += size[pb];
        }
    }
}