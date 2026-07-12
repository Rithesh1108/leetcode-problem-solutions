class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int result[]=new int[m+n];
        int i=0,j=0,k=0;
        while(i<m && j<n){
            if(nums1[i]<nums2[j]){
                result[k]=nums1[i];
                i++;
                k++;
            }
            else{
                result[k]=nums2[j];
                j++;
                k++;
            }
        }

        while(i<m){
            result[k]=nums1[i];
            i++;
            k++;
        }
        while(j<n){
            result[k]=nums2[j];
            j++;
            k++;
        }
        float med=result.length%2;
        if(med==1){
            return result[result.length/2];
        }
        else{
            double me=((result[(result.length/2)-1]+result[(((result.length/2)-1)+1)])/2.0);
            return me;
        }
    }
}