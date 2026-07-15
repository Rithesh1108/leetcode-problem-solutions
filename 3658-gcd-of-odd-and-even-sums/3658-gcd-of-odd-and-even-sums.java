class Solution {
    public int gcdOfOddEvenSums(int n) {
        int sumOdd=0;
        int sumEven=0;
        for(int i=0;i<=n*2;i++){
            if(i%2!=0){
                sumOdd=sumOdd+i;
            }
            else{
                sumEven=sumEven+i;
            }
        }
        int countO=0;
        int countE=0;
        for(int i=2;i<sumOdd;i++){
            if(sumOdd%i==0){
                countO++;
            }
        }
        for(int i=2;i<sumEven;i++){
            if(sumEven%i==0){
                countE++;
            }
        }
        int a[]=new int[countO];
        int b[]=new int[countE];
        int indexO=0;
        for(int i=2;i<sumOdd;i++){
            if(sumOdd%i==0){
                a[indexO]=i;
                indexO++;
            }
        }
        int indexE=0;
        for(int i=2;i<sumEven;i++){
            if(sumEven%i==0){
                b[indexE]=i;
                indexE++;
            }
        }
        int largest=0;
        int gcd=1;
        for(int i=0;i<=a.length-1;i++){
            for(int j=0;j<=b.length-1;j++){
                if(a[i]==b[j]){
                    gcd=a[i];
                }
            }
        }
        return gcd;
    }
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.gcdOfOddEvenSums(4));
    }
}