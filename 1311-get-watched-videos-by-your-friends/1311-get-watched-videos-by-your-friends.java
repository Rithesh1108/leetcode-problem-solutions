import java.util.*;

class Solution {
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos,
                                               int[][] friends,
                                               int id,
                                               int level) {

        int n = friends.length;

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(id);
        visited[id] = true;

        int currLevel = 0;

        while (!queue.isEmpty() && currLevel < level) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {

                int person = queue.poll();

                for (int friend : friends[person]) {

                    if (!visited[friend]) {
                        visited[friend] = true;
                        queue.offer(friend);
                    }
                }
            }

            currLevel++;
        }

        Map<String, Integer> map = new HashMap<>();

        while (!queue.isEmpty()) {

            int person = queue.poll();

            for (String video : watchedVideos.get(person)) {
                map.put(video, map.getOrDefault(video, 0) + 1);
            }
        }

        List<String> ans = new ArrayList<>(map.keySet());

        Collections.sort(ans, (a, b) -> {
            if (map.get(a).equals(map.get(b))) {
                return a.compareTo(b);
            }
            return map.get(a) - map.get(b);
        });

        return ans;
    }
}