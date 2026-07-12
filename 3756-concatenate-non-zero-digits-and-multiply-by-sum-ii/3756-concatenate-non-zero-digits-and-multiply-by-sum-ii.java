
class Solution {
    static final int MOD = 1_000_000_007;
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                posList.add(i);
                digitList.add(d);
            }
        }
        int k = digitList.size();
        int[] pos = new int[k];
        long[] prefixSum = new long[k + 1];
        long[] prefixValue = new long[k + 1];
        long[] pow10 = new long[k + 1];
        pow10[0] = 1;
        for (int i = 1; i <= k; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }
        for (int i = 1; i <= k; i++) {
            pos[i - 1] = posList.get(i - 1);
            int d = digitList.get(i - 1);
            prefixSum[i] = prefixSum[i - 1] + d;
            prefixValue[i] = (prefixValue[i - 1] * 10 + d) % MOD;
        }
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;
            if (left > right) {
                ans[i] = 0;
                continue;
            }
            int len = right - left + 1;
            long x = (prefixValue[right + 1]
                    - (prefixValue[left] * pow10[len]) % MOD
                    + MOD) % MOD;
            long sum = prefixSum[right + 1] - prefixSum[left];
            ans[i] = (int) ((x * (sum % MOD)) % MOD);
        }
        return ans;
    }
    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] <= target)
                l = mid + 1;
            else
                r = mid;
        }
        return l;
    }
}