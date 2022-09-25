package basic.class10;

import java.util.Stack;
import java.util.concurrent.locks.StampedLock;

/**
 * 判断链表是否是回文结构
 *
 * @author zhangling  2021/9/4 17:27
 */
public class Code03_IsPalindromeList {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(4);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.println(isPalindrome2(head));
        printLinkedList(head);
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // 使用栈判断是否是回文
    public static boolean isPalindrome1(Node head) {
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // 额外空间复杂度为 O(1)
    public static boolean isPalindrome2(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        // 定义快慢指针
        Node n1 = head;
        Node n2 = head;
        // 步骤1：使用快慢指针找出中点，快指针走两步，慢指针走一步
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;           // n1 -> mid
            n2 = n2.next.next;      // n2 -> end
        }
        n2 = n1.next;           // n2 指向右边区的第一个节点
        n1.next = null;         // mid.next -> null

        // 步骤2：将右边区域链表反转
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }
        n3 = n1;        // 最后一个节点
        n2 = head;      // 第一个节点

        // 步骤3：遍历左右两个区域的链表
        boolean res = true;
        while (n1 != null && n2 != null) {
            if (n1.val != n2.val) {
                res = false;        // 无论是否是回文，最后需要将链表反转回来
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }
        // 步骤4：复原反转的链表
        n1 = n3.next;          // n3 最后一个节点
        n3.next = null;
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        return res;

    }

}

