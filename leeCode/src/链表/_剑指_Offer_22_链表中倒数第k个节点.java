package 链表;

public class _剑指_Offer_22_链表中倒数第k个节点 {
	
	public ListNode getKthFromEnd(ListNode head, int k) {
		
		ListNode tempListNode = head;
		for (int i = 0; i < k-1; i++) {
			tempListNode = tempListNode.next;
			if (tempListNode == null) {
				return null;
			}
		}
		
		while (tempListNode.next != null) {
			head = head.next;
			tempListNode = tempListNode.next;
		}
		
        return head;
    }
}
