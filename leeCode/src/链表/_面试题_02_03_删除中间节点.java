package 链表;

public class _面试题_02_03_删除中间节点 {
	public void deleteNode(ListNode node) {
		if (node == null || node.next == null) return;
			
        ListNode l1 = node;
        ListNode l2 = node.next;
        ListNode prev = node;
        while (l2 != null && l2.next != null) {
        	l2 = l2.next.next;
        	prev = l1;
        	l1 = l1.next;
		}
        prev.next = l1.next;
        node = l1;
    }
}
