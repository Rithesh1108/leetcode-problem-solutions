class Solution {
    public int minimumCost(int[] nums, int k) {
        long mod=1_000_000_007L;
        long re=k;
        long op=0;
        long cost=0;
        for(int x:nums){
            if(re<x){
                long need=x-re;
                long time=(need+k-1)/k;
                long f=op+1;
                long l=op+time;
                long inv=500000004L;
                long a=(f%mod);
                long b=(l%mod);
                long c=(time%mod);
                long sum=(((a+b)%mod)*c)%mod;
                sum=(sum*inv)%mod;
                cost=(cost+sum%mod)%mod;
                op=op+time;
                re=re+time*(long)k;
            }
            re=re-x;
        }
        return (int)(cost%mod);
    }
}