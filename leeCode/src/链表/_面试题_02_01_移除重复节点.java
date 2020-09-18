package 链表;

import java.util.HashSet;
import java.util.Set;

public class _面试题_02_01_移除重复节点 {
	public ListNode removeDuplicateNodes(ListNode head) {
		Set<Integer> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            set.add(cur.val);
            if (set.contains(cur.next.val))
                cur.next = cur.next.next;
            else
                cur = cur.next;
        }
        return head;
    }
}
