class Solution {

    int max = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return max;
    }

    public int dfs(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int left = dfs(root.left);
        int right = dfs(root.right);

        int leftPath = 0;
        int rightPath = 0;

        if (root.left != null && root.left.val == root.val) {
            leftPath = left + 1;
        }

        if (root.right != null && root.right.val == root.val) {
            rightPath = right + 1;
        }

        max = Math.max(max, leftPath + rightPath);

        return Math.max(leftPath, rightPath);
    }
}