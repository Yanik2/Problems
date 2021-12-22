/*
 * Given the root of binary tree, return the inorder traversal of its nodes values.
 */

 // Definition for a binary tree node
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

public class BinaryTreeInorderTraversal {
       public List<Integer> inorderTraversal(TreeNode root) {
	       List<Integer> list = new ArrayList<>();
	       inOrder(root, list);
	       return list;
       }

       private void inOrder(TreeNode node, List<Integer> list) {
	       if(node == null) return;
	       inOrder(node.left, list);
	       list.add(node.val);
	       inOrder(node.right, list);
       }
}       
