package basic.class10;

/**
 * 将链表分成 <区，=区，>区
 * @author zhangling  2021/9/4 19:17
 */
public class Code02_SmallerEqualBigger {
    public static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    // 使用数组 对链表分区  pivot：划分值
    public static Node listParttion1(Node head, int pivot) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }
        Node[] nodeArr = new Node[i];
        cur = head;
        // 链表Node 放入数组
        for (i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrParttion(nodeArr, pivot);
        // 将 Node 数组节点组成链表
        for (i = 1; i < nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    private static void arrParttion(Node[] nodeArr, int pivot) {
        int samll = -1;
        int index = 0;
        int big = nodeArr.length;
        while (index != big) {
            if (nodeArr[index].val < pivot) {
                swap(nodeArr, ++samll, index++);
            } else if (nodeArr[index].val > pivot) {
                swap(nodeArr, --big, index);
            } else {
                index++;
            }
        }
    }

    // 链表划分区域，额外空间复杂度 O(1)
    public static Node listParttion2(Node head, int pivot) {
        Node smallHead = null;
        Node smallTail = null;
        Node equalHead = null;
        Node equalTail = null;
        Node bigHead = null;
        Node bigTail = null;

        Node next = null;
        // 划分成 <区，=区，>区
        while (head != null) {
            next = head.next;
            head.next = null;       // 把头节点断开
            if (head.val < pivot) {
                if (smallHead == null) {
                    smallHead = head;
                    smallTail = head;
                } else {
                    smallTail.next = head;
                    smallTail = head;
                }
            } else if (head.val == pivot) {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = head;
                }
            } else {
                if (bigHead == null) {
                    bigHead = head;
                    bigTail = head;
                } else {
                    bigTail.next = head;
                    bigTail = head;
                }
            }
            head = next;
        }
        // <区尾巴，连 =区头，=区尾巴连 >区头
        if (smallTail != null) {
            smallTail.next = equalHead;
            equalTail = equalTail == null ? smallTail : equalTail;  // 下一步，谁去连大于区的头
        }
        // 有=区，=区连接 >区
        if (equalTail != null) {
            equalTail.next = bigHead;
        }
        return smallHead != null ? smallHead : (equalHead != null ? equalHead : bigHead);
    }

    public static void swap(Node[] arr, int i, int j) {
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        head.next = new Node(9);
        head.next.next = new Node(1);
        head.next.next.next = new Node(8);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(2);
        head.next.next.next.next.next.next = new Node(5);
        printLinkedList(head);
        head = listParttion2(head, 5);
        printLinkedList(head);
    }

    public static void printLinkedList(Node head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }


}

