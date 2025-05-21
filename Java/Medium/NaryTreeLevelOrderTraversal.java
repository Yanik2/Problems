/**
 *Given an n-ary tree, return the level order traversal of its nodes' values.

Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value
 */

public class NaryTreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(Node root) {
        if (root == null) return List.of();
        if (root.children == null || root.children.isEmpty())
            return List.of(List.of(root.val));

        final var queue = new Queue();
        queue.add(root);
        var parentQueue = new Queue();
        parentQueue.add(root);
        final List<List<Integer>> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            queue.poll();
            var childQueue = new Queue();
            final List<Integer> levelList = new ArrayList<>();

            while (!parentQueue.isEmpty()) {
                final Node parent = parentQueue.poll();
                levelList.add(parent.val);
                if (parent.children != null && !parent.children.isEmpty()) {
                    for (Node child : parent.children) {
                        childQueue.add(child);
                        queue.add(child);
                    }
                }
            }

            parentQueue = childQueue;
            if (!levelList.isEmpty())
                result.add(levelList);
        }

        return result;
     }

    class Queue {
        QueueNode first;
        QueueNode last;
        int size;

        void add(Node x) {
            final QueueNode newNode = new QueueNode();
            newNode.val = x;

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

        Node poll() {
            Node val = last.val;
            last = last.prev;
            size--;
            return val;
        }

        class QueueNode {
            QueueNode next;
            QueueNode prev;
            Node val;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }
}
