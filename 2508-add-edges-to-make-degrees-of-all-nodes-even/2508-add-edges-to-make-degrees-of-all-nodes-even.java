import java.util.*;

class Solution {
    public boolean isPossible(int n, List<List<Integer>> edges) {

        List<Set<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new HashSet<>());
        }

        int[] degree = new int[n + 1];

        for (List<Integer> edge : edges) {
            int u = edge.get(0);
            int v = edge.get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);

            degree[u]++;
            degree[v]++;
        }

        List<Integer> odd = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (degree[i] % 2 == 1) {
                odd.add(i);
            }
        }

        if (odd.size() == 0) {
            return true;
        }

        if (odd.size() == 2) {

            int a = odd.get(0);
            int b = odd.get(1);

            if (!graph.get(a).contains(b)) {
                return true;
            }

            for (int i = 1; i <= n; i++) {
                if (i != a && i != b &&
                    !graph.get(a).contains(i) &&
                    !graph.get(b).contains(i)) {
                    return true;
                }
            }

            return false;
        }

        if (odd.size() == 4) {

            int a = odd.get(0);
            int b = odd.get(1);
            int c = odd.get(2);
            int d = odd.get(3);

            if (!graph.get(a).contains(b) && !graph.get(c).contains(d))
                return true;

            if (!graph.get(a).contains(c) && !graph.get(b).contains(d))
                return true;

            if (!graph.get(a).contains(d) && !graph.get(b).contains(c))
                return true;
        }

        return false;
    }
}