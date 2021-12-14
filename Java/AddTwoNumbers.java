/*
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 intself.
 *
 * public class ListNode {
 * 	int val;
 * 	ListNode next;
 * 	ListNode() {}
 * 	ListNode(int val) {
 * 		this.val = val;
 * 	}
 * 	ListNode(int val, ListNode next) {
 * 		this.val = val;
 * 		this.next = next;
 * 	}
 * }
 */



public class AddTwoNumbers {
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode head = l1;
		ListNode prev = head;
		int temp = 0;

		while(l1 != null && l2 != null) {
			temp += l1.val + l2.val;
			l1.val = temp % 10;
			temp /= 10;
			prev = l1;
			l1 = l1.next;
			l2 = l2.next;
		}

		if(l1 != null) {
			while(temp > 0 && l1 != null) {
				temp += l1.val;
				l1.val = temp % 10;
				temp /= 10;
				prev = l1;
				l1 = l1.next;
			}
		}

		if(l2 != null) {
			while(l2 != null) {
				temp += l2.val;
				l2.val = temp % 10;
				temp /= 10;
				prev.next = l2;
				prev = prev.next;
				l2 = l2.next;
			}
		}

		prev.next = temp > 0 ? new ListNode(temp) : prev.next;
		return head;
	}
}
