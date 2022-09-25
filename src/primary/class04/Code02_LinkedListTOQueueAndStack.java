package primary.class04;

import java.util.ResourceBundle;
import java.util.SimpleTimeZone;

/**
 * 单链表实现队列和栈结构
 * 时间复杂度 O(1)
 *
 * @author zhangling 2021/3/1 14:37
 */
public class Code02_LinkedListTOQueueAndStack {

    public static class Node<V> {
        public V value;
        public Node<V> next;

        public Node(V value) {
            this.value = value;
            next = null;
        }
    }

    /**
     * 单链表实现队列
     * @param <V>
     */
    public static class MyQueue<V> {
        private Node<V> head;
        private Node<V> tail;
        private int size;

        public MyQueue() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        /**
         * 向队列添加元素
         *
         * @param value
         */
        public void offer(V value) {
            Node cur = new Node(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                tail = cur;
            }
            size++;
        }

        // 取对列中头个元素,并从队列中移除，如果取到null返回null
        public V poll() {
            V ans = null;
            if (head == null) {
                return null;
            } else {
                ans = head.value;
                head = head.next;
                size--;
            }
            return ans;
        }

        // 取队列中头部元素，但不从队列中移除
        public V peek() {
            if (head == null) {
                return null;
            } else {
                return head.value;
            }
        }
    }


    /**
     * 单链表实现栈
     * @param <V>
     */
    public static class MyStack<V> {
        private Node<V> head;
        private int size;

        public MyStack() {
            head = null;
            size = 0;
        }

        public void push(V v) {
            Node<V> cur = new Node<>(v);
            if (head == null) {
                head = cur;
            } else {
                cur.next = head;
                head = cur;
            }
            size++;
        }

        public V pop() {
            V ans = null;
            if (head != null) {
                ans = head.value;
                head = head.next;
            }
            return ans;
        }

        public V peek() {
            return head == null ? null : head.value;
        }
    }

}
