class Solution {
    public long sumAndMultiply(int n) {
        String s = String.valueOf(n);
        long x = 0;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                x = x * 10 + digit;  
                sum += digit;       
            }
        }
        return x * sum;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.sumAndMultiply(10203004)); 
        System.out.println(s.sumAndMultiply(1000));     
        System.out.println(s.sumAndMultiply(67087)); 
    }
}