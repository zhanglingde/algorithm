package class10;

import java.util.HashMap;

/**
 * Ramdon 指针链表复制
 * @author zhangling  2021/9/5 15:40
 */
public class Code04_CopyListWithRandom {
    public static class Node {
        int value;
        Node next;
        Node random;

        public Node(int value) {
            this.value = value;
        }
    }

    // 使用 HashMap 容器辅助
    public static Node copyRandomList1(Node head) {
        // key 老节点
        // value 新节点
        HashMap<Node, Node> map = new HashMap<>();
        // 步骤1：复制一份节点
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        // 步骤2：将节点next，random指针指向正确位置
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // 使用有限个变量
    public static Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        Node next = null;

        // 步骤1：拷贝节点，维护next指针
        // 1 -> 2 -> 3 -> null
        // 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
            cur = next;
        }
        // 步骤2：一对对取节点，将copy节点的random指针指向正确位置
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }
        // 步骤3：将原节点和 copy节点分离
        Node res = head.next;
        cur = head;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
