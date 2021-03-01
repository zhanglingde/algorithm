package class04;

import java.util.List;

/**
 * 两链表相加
 *
 * @author zhangling 2021/3/1 16:44
 */
//测试链接：https://leetcode.com/problems/add-two-numbers/
public class Code05_AddTwoNumbers {

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
        int len1 = listLength(head1);
        int len2 = listLength(head2);

        // 先找出长链表
        ListNode l = len1 >= len2 ? head1 : head2;
        ListNode s = l == head1 ? head2 : head1;

        ListNode curL = l;
        ListNode curS = s;
        ListNode last = curL;       // 当最后长链表和短链表都为null，且进位为1时，需要在链表最后一位加1，last是链表最后一个节点的信息
        int carry = 0;          // 进位
        int curNum = 0;
        while (curS != null) {
            curNum = curL.val + curS.val + carry;
            curL.val = curNum % 10;
            carry = curNum / 10;
            last = curL;
            curL = curL.next;
            curS = curS.next;
        }
        while (curL != null) {
            curNum = curL.val+carry;
            curL.val = curNum %10;
            carry = curNum/10;
            last = curL;
            curL = curL.next;
        }
        while (carry != 0){
            last.next = new ListNode(1);
        }

        return l;
    }

    // 计算链表长度
    public static int listLength(ListNode node) {
        int length = 0;
        if (node != null) {
            length++;
            node = node.next;
        }
        return length;
    }
}
