package basic.class02;

/**
 * 单链表、双链表反转
 * @author zhangling 2021/7/6 13:33
 */
public class ReverseList {
    public static class Node{
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }
    public static class DoubleNode{
        public int val;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode(int val) {
            this.val = val;
        }
    }

    /**
     * 单链表反转
     * @param head
     * @return
     */
    public static Node reverseLinkedList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 双向链表反转
     * @param head 原链表头指针
     * @return 返回反转后链表的头指针
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;

            head.next = pre;
            head.last = next;
            pre = head;

            head = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        //Node node1 = new Node(1);
        //node1.next = new Node(2);
        //node1.next.next = new Node(3);
        //Node hed = node1;
        //while (hed != null) {
        //    System.out.println(hed.val);
        //    hed = hed.next;
        //}
        //Node head = reverseLinkedList(node1);
        //while (head != null) {
        //    System.out.println(head.val);
        //    head = head.next;
        //}

        testReverseDoubleNode();
    }

    public static void testReverseDoubleNode() {
        DoubleNode head = new DoubleNode(1);
        head.next = new DoubleNode(2);
        head.next.last = head;
        head.next.next = new DoubleNode(3);
        head.next.next.last = head.next;

        DoubleNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.val+" ");
            tmp = tmp.next;
        }
        System.out.println();
        DoubleNode tail = reverseDoubleList(head);
        DoubleNode temp = tail;
        while (temp != null) {
            System.out.print(temp.val+" ");
            temp = temp.next;
        }
    }
}
