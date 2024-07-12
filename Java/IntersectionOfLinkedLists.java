/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class IntersectionOfLinkedLists {
	public ListNode getIntersection(ListNode headA, ListNode headB) {
		var currentA = headA;
		var currentB = headB;

		while(currentA != null) {
			while(currentB != null) {
				if (currentA == currentB) {
					return currentA;
				}
				currentB = currentB.next;
			}

			currentA = currentA.next;
			currentB = headB;
		}

		return currentA;
	}
}
