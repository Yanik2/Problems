/**
 *You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

This is Breadth First Search approach.
 */

public class PopulatingNextRightPointersInEachNode {
	public Node connect(Node root) {
        if (root == null || root.left == null) return root;

        int level = 1;
        final Queue queue = new Queue();
        queue.add(root);

        while (!queue.isEmpty()) {
            for (int i = 0; i < level; i++) {
                final Node node = queue.pop();
                queue.add(node.left);
                queue.add(node.right);
                if (i < level - 1)
                    node.next = queue.peek();
            }

            level *= 2;
        }

        return root;
    }



    class Queue {
        private QueueNode first;
        private QueueNode last;
        private int size;

        void add(Node value) {
            if (value == null) {
                return;
            }
            final QueueNode newNode = new QueueNode();
            newNode.value = value;
            if (isEmpty()) {
                first = newNode;
                last = newNode;
            } else {
                newNode.next = first;
                first.prev = newNode;
                first = newNode;
                if (last.prev == null) {
                    last.prev = newNode;
                }
            }

            size++;
        }

        Node pop() {
            final Node value = last.value;
            last = last.prev;
            size--;
            return value;
        }

        Node peek() {
            return last == null ? null : last.value;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class QueueNode {
            Node value;
            QueueNode next;
            QueueNode prev;
        }
    }
}
