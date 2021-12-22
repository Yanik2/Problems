/*
 * Given the root of a binary tree and an integer targetSum, return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
 * A leaf is a node with no children.
 */

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	TreeNode() {}
	TreeNode(int val) { this.val = val; }
	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
} 

public class PathSum {
	public boolean hasPathSum(TreeNode root, int targetSum) {
		if(root == null) return false;
		if(targetSum - root.val = 0 && hasChildren(root)) return true;
		return hasPahtSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
	}

	private boolean hasChildren(TreeNode node) {
		return node.left == null && node.right == null;
	}
}
