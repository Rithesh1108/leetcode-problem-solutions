class Solution {
    public int kConcatenationMaxSum(int[] arr, int k) {
        int MOD = 1_000_000_007;

        long totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        long maxEndingHere = 0;
        long maxSoFar = 0;

        int times = (k == 1) ? 1 : 2;

        for (int t = 0; t < times; t++) {
            for (int num : arr) {
                maxEndingHere = Math.max(0, maxEndingHere + num);
                maxSoFar = Math.max(maxSoFar, maxEndingHere);
            }
        }

        if (k > 2 && totalSum > 0) {
            maxSoFar += (long) (k - 2) * totalSum;
        }

        return (int) (maxSoFar % MOD);
    }
}