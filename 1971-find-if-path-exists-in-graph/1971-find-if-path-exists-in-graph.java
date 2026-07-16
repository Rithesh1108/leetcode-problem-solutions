import java.util.*;

class Solution {

    List<Integer>[] graph;
    boolean[] visited;

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        visited = new boolean[n];

        return dfs(source, destination);
    }

    private boolean dfs(int node, int destination) {

        if (node == destination)
            return true;

        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                if (dfs(next, destination))
                    return true;
            }
        }

        return false;
    }
}