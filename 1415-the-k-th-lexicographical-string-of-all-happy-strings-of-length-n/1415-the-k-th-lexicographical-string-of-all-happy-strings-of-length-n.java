import java.util.*;

class Solution {

    List<String> list = new ArrayList<>();

    public String getHappyString(int n, int k) {

        backtrack(n, new StringBuilder());

        if (k > list.size()) {
            return "";
        }

        return list.get(k - 1);
    }

    private void backtrack(int n, StringBuilder sb) {

        if (sb.length() == n) {
            list.add(sb.toString());
            return;
        }

        char[] ch = {'a', 'b', 'c'};

        for (char c : ch) {

            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == c) {
                continue;
            }

            sb.append(c);
            backtrack(n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}