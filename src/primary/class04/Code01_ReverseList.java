package primary.class04;

/**
 * 反转链表
 *
 * @author zhangling 2021/3/1 11:36
 */
public class Code01_ReverseList {
    public static class Node{
        public int value;
        public Node next;

        public Node(int data){
            value = data;
        }
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode last;
        public DoubleNode next;


        public DoubleNode(int data){
            value = data;
        }

    }

    public static void main(String[] args) {
        //Node n1 = new Node(1);
        //n1.next = new Node(2);
        //n1.next.next = new Node(3);
        //n1 = reverseList(n1);

        DoubleNode n1 = new DoubleNode(1);
        DoubleNode n2 = new DoubleNode(2);
        DoubleNode n3 = new DoubleNode(3);

        n1.next = n2;
        n2.next = n3;
        n3.last = n2;
        n2.last = n1;

        n1  = reverseDoubleList(n1);

        while (n1 != null) {
            System.out.print(n1.value+" ");
            n1 = n1.next;
        }
        System.out.println();
        while (n3 != null) {
            System.out.print(n3.value+" ");
            n3 = n3.last;
        }

        //
    }

    /**
     * 逆序函数，返回逆序后的头节点
     * @param head
     * @return
     */
    private static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;      // 记下下一个节点
            head.next = pre;      // 反转
            pre = head;
            head  =next;


        }

        return pre;
    }

    /**
     * 双向链表逆序
     * @param head
     * @return
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode last = null;

        while (head != null) {
            // 记录原节点信息
            next = head.next;
            // 改变后的节点信息
            head.next = last;
            head.last = next;
            // 改变后下一个节点的信息
            last = head;
            head = next;

        }
        return last;
    }
}
