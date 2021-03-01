package class04;

/**
 * 合并两个有序链表
 *
 * @author zhangling 2021/3/1 17:15
 */
public class Code06_MergeTwoSortedLinkedList {

    public static class ListNode {
        private int val;
        private ListNode next;
    }

    public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {
        if (head1 == null || head2 == null) {
            return head1 == null ? head2 : head1;
        }
        ListNode head = head1.val <= head2.val ? head1 : head2;     // 最小的头节点的位置
        ListNode cur1 = head.next;                                  // 最小的头节点链表的下一个节点的位置
        ListNode cur2 = head == head1 ? head2 : head1.next;         // 另一个链表（头节点不是最小节点）的第一个节点位置
        ListNode pre = head;
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre.next = cur1;
                cur1 = cur1.next;

            } else {
                pre.next = cur2;
                cur2 = cur2.next;
            }
            pre = pre.next;
        }

        pre.next = cur1 == null ? cur2 : cur1;
        return head;

    }
}
