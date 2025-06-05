/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class ConvertSortedArrayToBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
        final boolean[] visited = new boolean[nums.length];

        final TreeNode root = new TreeNode(nums[nums.length / 2]);
        visited[nums.length / 2] = true;
        setChildren(root, nums.length / 2, 0, nums.length, nums, visited);
        return root;
    }

    private void setChildren(TreeNode node, int index, int start, int len, int[] nums, boolean[] visited) {
        int leftChild = (start + index) / 2;
        int rightChild = (len + index) / 2;

        if (visited[leftChild] && visited[rightChild]) {
            return;
        }

        if (visited[leftChild]) {
            node.left = null;
        } else {
            node.left = new TreeNode(nums[leftChild]);
            visited[leftChild] = true;
        }

        if (visited[rightChild]) {
            node.right = null;
        } else {
            node.right = new TreeNode(nums[rightChild]);
            visited[rightChild] = true;
        }

        setChildren(node.left, leftChild, start, index, nums, visited);
        setChildren(node.right, rightChild, index, len, nums, visited);
    }
}
