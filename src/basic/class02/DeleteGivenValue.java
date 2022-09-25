package basic.class02;

import java.net.Inet4Address;

/**
 * 删除链表中给定的值
 * @author zhangling 2021/7/6 14:11
 */
public class DeleteGivenValue {
    public static class Node{
        public int value;
        public Node next;

        public Node(int val) {
            this.value = val;
        }
    }

    /**
     * 删除链表中和给定值相等的节点
     * @param head 原链表头节点
     * @param num 需要删除的值
     * @return 返回新链表头节点
     */
    public static Node deleteGivenValue(Node head, int num) {
        // 循环判断头部是否是 num
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
        // 删除链表中的 num
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        Node node2 = new Node(0);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(3);
        Node node6 = new Node(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        printNode(node1);
        Node head = deleteGivenValue(node1, 3);
        printNode(head);

    }

    public static void printNode(Node head) {
        Node tmp = head;
        while (tmp != null) {
            System.out.print(tmp.value+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }
}
