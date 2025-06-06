/**
 *Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 */

public class RemoveLinkedListElements {
	public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;

        ListNode current = head;
        ListNode prev = null;

        while (current != null) {

            if (current.val == val) {
                if (prev != null) {
                    prev.next = current.next;
                    current = current.next;
                }
                else {
                    head = head.next;
                    current = head;
                }
            } else {
                prev = current;
                current = current.next;
            }
        }

        return head;
    }
}
