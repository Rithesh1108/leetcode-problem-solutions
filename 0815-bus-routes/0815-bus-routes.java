import java.util.*;

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {

        if (source == target)
            return 0;

        Map<Integer, List<Integer>> stopToBus = new HashMap<>();

        for (int bus = 0; bus < routes.length; bus++) {
            for (int stop : routes[bus]) {
                stopToBus.computeIfAbsent(stop, k -> new ArrayList<>()).add(bus);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedStops = new HashSet<>();
        boolean[] visitedBus = new boolean[routes.length];

        queue.offer(source);
        visitedStops.add(source);

        int buses = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            buses++;

            while (size-- > 0) {

                int stop = queue.poll();

                List<Integer> busList = stopToBus.get(stop);
                if (busList == null)
                    continue;

                for (int bus : busList) {

                    if (visitedBus[bus])
                        continue;

                    visitedBus[bus] = true;

                    for (int nextStop : routes[bus]) {

                        if (nextStop == target)
                            return buses;

                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.offer(nextStop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}