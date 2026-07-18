class Solution {
    public int findGCD(int[] nums) {
        int s=nums[0];
        int l=0;
        for(int i=0;i<=nums.length-1;i++){
            if(nums[i]>l){
                l=nums[i];
            }
            if(nums[i]<s){
                s=nums[i];
            }
        }
        int gcd=0;
        for(int i=1;i<=l;i++){
            if(s%i==0&&l%i==0){
                gcd=i;
            }
        }
        return gcd;
    }

public void main(String[] args){
    int nums[]={2,5,6,9,10};

    System.out.println(findGCD(nums));
}
}