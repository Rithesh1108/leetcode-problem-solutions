import java.util.*;

class Solution {

    List<List<Integer>> ans = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {

        path.add(0);
        dfs(graph, 0);

        return ans;
    }

    private void dfs(int[][] graph, int node) {

        if (node == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int next : graph[node]) {
            path.add(next);
            dfs(graph, next);
            path.remove(path.size() - 1);
        }
    }
}