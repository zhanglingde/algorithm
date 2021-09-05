package test;

import class02.TwoStacksImplementQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhangling 2021/7/12 13:15
 */
public class Demo {

    class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node deleteGiveValue(Node head ,int num){
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        Node pre = head;
        Node cur = head;
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



    public static void main(String[] args) {
        int i = 10;
        while (i > 0) {
            if (--i > 5) {
                System.out.println(i);
            }
        }
        System.out.println("end...");

    }
}
