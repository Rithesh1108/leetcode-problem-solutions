class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        if (words[startIndex].equals(target)) {
            return 0;
        }

        int forward = Integer.MAX_VALUE;
        int backward = Integer.MAX_VALUE;

        for (int k = 1; k < n; k++) {
            int index = (startIndex + k) % n;
            if (words[index].equals(target)) {
                forward = k;
                break;
            }
        }

        for (int k = 1; k < n; k++) {
            int index = (startIndex - k + n) % n;
            if (words[index].equals(target)) {
                backward = k;
                break;
            }
        }
        if (forward == Integer.MAX_VALUE && backward == Integer.MAX_VALUE) {
            return -1;
        }

        return Math.min(forward, backward);
    }
}