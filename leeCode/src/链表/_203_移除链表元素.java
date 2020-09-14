package 链表;
/*
 * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/submissions/
 */
public class _203_移除链表元素 {
	
	public ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		
		if (head == null) return null;
		
		ListNode prevListNode = head;
		
		while (prevListNode.next != null) {
			if (prevListNode.next.val == val) {
				prevListNode.next = prevListNode.next.next;
			}else {
				prevListNode = prevListNode.next;
			}
		}
		
		return head;
    }
	
	
	public ListNode removeElements2(ListNode head, int val) {
		while(head != null && head.val == val){
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if(head == null){
            return head;
        }

        ListNode prev = head;
        while(prev.next != null){
            if(prev.next.val == val){
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            }else{
                prev = prev.next;
            }
        }

        return head;
    }
	
	/// 增加虚拟头结点
	public ListNode removeElements3(ListNode head, int val) {
		ListNode newHead = new ListNode(0);
	    newHead.next = head;
	    ListNode temp = newHead;

	    while (temp.next != null) {
	        if (temp.next.val == val) {
	            temp.next = temp.next.next;
	        } else {
	            temp = temp.next;
	        }
	    }

	    return newHead.next;
	}
	
	
}
