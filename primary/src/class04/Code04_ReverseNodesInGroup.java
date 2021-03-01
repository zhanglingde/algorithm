package class04;

/**
 * K个节点组内逆序调整
 *
 * @author zhangling 2021/3/1 15:55
 */
// 测试链接：https://leetcode.com/problems/reverse-nodes-in-k-group/
public class Code04_ReverseNodesInGroup {

    public static class ListNode {
        public int val;
        public ListNode next;
    }

    /**
     *
     * @param start 开始节点位置
     * @param k
     * @return 返回一组节点的最后一个节点，如果不够分为一组返回null
     */
    public static ListNode getKGroupEnd(ListNode start, int k) {
        while (--k != 0 && start != null) {
            start = start.next;
        }
        return start;
    }

    // 组内逆序
    public static void reverse(ListNode start, ListNode end) {
        end = end.next;         // 先记录下一个组第一个的位置
        ListNode pre = null;
        ListNode next = null;
        ListNode cur = start;
        while (cur != end) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        start.next = end;           // 当前组的第一个指向下一个组的第一个
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode start = head;
        ListNode end = getKGroupEnd(start, k);
        if (start == null) {
            return head;
        }
        // 第一组凑齐了
        head = end;
        reverse(start,end);
        ListNode lastEnd = start;     // 上一组的结尾的节点

        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = getKGroupEnd(start, k);
            if (end == null) {
                return head;
            }
            reverse(start,end);
            lastEnd.next = end;
            lastEnd = start;
        }

        return head;
    }


}
