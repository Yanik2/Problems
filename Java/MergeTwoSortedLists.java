class MergeTwoSortedLists {
  public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode first = new ListNode();

        if(l1.val >= l2.val) {
            first.val = l2.val;
            first.next = mergeTwoLists(l1, l2.next);
        }
        else {
            first.val = l1.val;
            first.next = mergeTwoLists(l1.next, l2);
        }
        return first;
    }
}
