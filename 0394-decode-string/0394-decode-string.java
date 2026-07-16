class Solution {

    int index = 0;

    public String decodeString(String s) {
        return helper(s);
    }

    private String helper(String s) {

        StringBuilder result = new StringBuilder();
        int num = 0;

        while (index < s.length()) {

            char ch = s.charAt(index);

            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } 
            else if (ch == '[') {
                index++;
                String str = helper(s);

                while (num-- > 0) {
                    result.append(str);
                }
                num = 0;
            } 
            else if (ch == ']') {
                return result.toString();
            } 
            else {
                result.append(ch);
            }

            index++;
        }

        return result.toString();
    }
}