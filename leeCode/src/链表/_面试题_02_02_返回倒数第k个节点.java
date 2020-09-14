package 链表;

public class _面试题_02_02_返回倒数第k个节点 {
	public int kthToLast(ListNode head, int k) {
        
		ListNode tempListNode = head;
		for (int i = 0; i < k - 1; i++) {
			tempListNode = tempListNode.next;
		}
		
		while (tempListNode.next != null) {
			tempListNode = tempListNode.next;
			head = head.next;
		}
		
		return head.val;
        
    }
}
