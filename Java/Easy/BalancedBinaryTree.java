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

public class BalancedBinaryTree {
	public boolean isBalanced(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;

        int leftDepth = getDepth2(root.left, 0);
        int rightDepth = getDepth2(root.right, 0);

        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        };

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private int getDepth2(TreeNode node , int depth) {
        if (node == null) {
            return depth;
        }
        if (node.left == null && node.right == null) {
            return depth + 1;
        }

        int leftDepth = node.left != null ? getDepth2(node.left, depth + 1) : depth;
        int rightDepth = node.right != null ? getDepth2(node.right, depth + 1) : depth;

        return Math.max(leftDepth, rightDepth);
    }
}
