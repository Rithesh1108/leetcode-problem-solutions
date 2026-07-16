import java.util.*;

class Solution {
    public int timeRequiredToBuy(int[] tickets, int k) {

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < tickets.length; i++) {
            queue.offer(new int[]{i, tickets[i]});
        }

        int time = 0;

        while (!queue.isEmpty()) {

            int[] person = queue.poll();

            person[1]--;
            time++;

            if (person[1] == 0) {
                if (person[0] == k) {
                    return time;
                }
            } else {
                queue.offer(person);
            }
        }

        return time;
    }
}