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

public class MinimumDepthOfBinaryTree {
	public int minDepth(TreeNode root) {
        if (root == null) return 0;

        final Queue queue = new Queue();

        queue.add(root);
        TreeNode currentNode;
        int counter = 0;
        int levelElements = 1;
        int nextLevelElements = 0;

        while(true) {
            counter++;
            for (int i = 0; i < levelElements; i++) {
                currentNode = queue.poll();

                if (currentNode.left == null && currentNode.right == null) {
                    return counter;
                }
                if (currentNode.left != null) {
                    queue.add(currentNode.left);
                    nextLevelElements++;
                }
                if (currentNode.right != null) {
                    queue.add(currentNode.right);
                    nextLevelElements++;
                }
            }
            levelElements = nextLevelElements;
            nextLevelElements = 0;
        }
    }

    class Queue {
        Node first;
        Node last;
        int size;

        void add(TreeNode value) {
            final Node newNode = new Node();
            newNode.value = value;

            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.prev = first;
                first.next = newNode;
                first = newNode;
                if (last.next == null) {
                    last.next = newNode;
                }
            }
            size++;
        }

        TreeNode poll() {
            final TreeNode value = last.value;
            last = last.next;
            size--;
            return value;
        }

        boolean isEmpty() {
            return this.size == 0;
        }
        class Node {
            TreeNode value;
            Node prev;
            Node next;
        }
    }
}
