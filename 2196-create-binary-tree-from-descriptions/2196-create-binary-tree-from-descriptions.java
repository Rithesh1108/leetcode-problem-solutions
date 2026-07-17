class Solution {
    public TreeNode createBinaryTree(int[][] descriptions) {

        HashMap<Integer, TreeNode> map = new HashMap<>();
        HashSet<Integer> child = new HashSet<>();

        for (int[] d : descriptions) {

            int parent = d[0];
            int childNode = d[1];
            int isLeft = d[2];

            if (!map.containsKey(parent)) {
                map.put(parent, new TreeNode(parent));
            }

            if (!map.containsKey(childNode)) {
                map.put(childNode, new TreeNode(childNode));
            }

            if (isLeft == 1) {
                map.get(parent).left = map.get(childNode);
            } else {
                map.get(parent).right = map.get(childNode);
            }

            child.add(childNode);
        }

        for (int[] d : descriptions) {
            int parent = d[0];

            if (!child.contains(parent)) {
                return map.get(parent);
            }
        }

        return null;
    }
}