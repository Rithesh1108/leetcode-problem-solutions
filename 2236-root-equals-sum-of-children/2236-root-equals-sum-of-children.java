// Root Equals Sum of Children: Check whether the value of the root node is equal to the sum of its left and right child values.

class Solution {
    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}