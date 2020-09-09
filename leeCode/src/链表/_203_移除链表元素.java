package 链表;

public class _203_移除链表元素 {
	
	
	public ListNode removeElements(ListNode head, int val) {
		ListNode newNode = head;
		while (head != null) {
			if (head.val == val) {
				newNode.next = newNode.next.next;
			}
			head = head.next;
		}
		return newNode;
    }
}
