import java.util.*;

class Solution {
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {

        boolean[] hasIncoming = new boolean[n];

        for (List<Integer> edge : edges) {
            hasIncoming[edge.get(1)] = true;
        }

        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (!hasIncoming[i]) {
                ans.add(i);
            }
        }

        return ans;
    }
}