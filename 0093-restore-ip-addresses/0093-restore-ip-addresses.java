import java.util.*;

class Solution {

    List<String> ans = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {

        backtrack(s, 0, 0, "");
        return ans;
    }

    private void backtrack(String s, int index, int parts, String path) {

        if (parts == 4 && index == s.length()) {
            ans.add(path.substring(0, path.length() - 1));
            return;
        }

        if (parts == 4 || index == s.length()) {
            return;
        }

        for (int len = 1; len <= 3 && index + len <= s.length(); len++) {

            String part = s.substring(index, index + len);

            if ((part.length() > 1 && part.charAt(0) == '0') ||
                Integer.parseInt(part) > 255) {
                continue;
            }

            backtrack(s, index + len, parts + 1, path + part + ".");
        }
    }
}