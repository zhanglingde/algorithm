package class06;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 多个有序的链表组成一个有序的链表
 */
// 测试链接：https://leetcode.com/problems/merge-k-sorted-lists/
public class Code01_MergeSortedLists {

    public static class ListNode {
        public int val;
        public ListNode next;
    }


    public static class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode n1, ListNode n2) {
            return n1.val - n2.val;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }

        // 把链表的第一个放入优先级队列
        PriorityQueue<ListNode> heap = new PriorityQueue<>(new ListNodeComparator());
        for (ListNode node : lists) {
            if (node != null) {   //
                heap.add(node);
            }
        }
        if (heap.isEmpty()) {       //
            return null;
        }

        ListNode cur = heap.poll();
        ListNode head = cur;
        if (cur.next != null) {
            heap.add(cur.next);
        }

        ListNode pre = head;

        while (!heap.isEmpty()) {
            cur = heap.poll();
            pre.next = cur;
            pre = pre.next;
            if (cur.next != null) {
                heap.add(cur.next);
            }

        }
        return head;
    }
}
