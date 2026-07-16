import java.util.*;

class Solution {
    public int countStudents(int[] students, int[] sandwiches) {

        Queue<Integer> queue = new LinkedList<>();

        for (int student : students) {
            queue.offer(student);
        }

        int index = 0;
        int count = 0;

        while (!queue.isEmpty() && count < queue.size()) {

            if (queue.peek() == sandwiches[index]) {
                queue.poll();
                index++;
                count = 0;
            } else {
                queue.offer(queue.poll());
                count++;
            }
        }

        return queue.size();
    }
}