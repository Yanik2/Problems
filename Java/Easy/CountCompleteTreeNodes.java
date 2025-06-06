/**
 *Given the root of a complete binary tree, return the number of the nodes in the tree.

According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Design an algorithm that runs in less than O(n) time complexity.
 */

public class CountCompleteTreeNodes {
	public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int level = 1;

        final Queue queue = new Queue();
        queue.add(root);

        while (true) {
            for (int i = 0; i < level; i++) {
                final TreeNode current = queue.poll();

                if (current.right == null) {
                    if (current.left != null) {
                        queue.add(current.left);
                    }

                    int lastLevelCount = queue.size - (level - (i + 1));
                    return level + (level - 1) + lastLevelCount;
                } else {
                    queue.add(current.left);
                    queue.add(current.right);
                }
            }

            level *= 2;
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
                this.first = newNode;
                this.last = newNode;
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
            return size == 0;
        }

        class Node {
            TreeNode value;
            Node next;
            Node prev;
        }
    }
}
