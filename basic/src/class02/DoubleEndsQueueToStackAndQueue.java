package class02;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双向链表实现栈和队列
 *
 * @author zhangling 2021/7/6 15:06
 */
public class DoubleEndsQueueToStackAndQueue {

    public static class Node<T> {
        public T val;
        public Node<T> last;
        public Node<T> next;

        public Node(T val) {
            this.val = val;
        }
    }

    /**
     * 双向链表实现队列
     *
     * @param <T>
     */
    public static class DoubleEndsQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        /**
         * 从头部添加
         *
         * @param value
         */
        public void addFromHead(T value) {
            Node<T> cur = new Node<T>(value);
            if (head == null) {
                head = cur;
                tail = cur;
            } else {
                cur.next = head;
                head.last = cur;
                head = cur;
            }
        }

        /**
         * 从尾部添加
         *
         * @param value
         */
        public void addFromTail(T value) {
            Node<T> cur = new Node<T>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                tail.next = cur;
                cur.last = tail;
                tail = cur;
            }
        }

        /**
         * 从头部弹出
         *
         * @return
         */
        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> cur = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                cur.next = null;
                head.last = null;
            }

            return cur.val;
        }

        /**
         * 从尾部弹出
         *
         * @return
         */
        public T popFromTail() {
            if (tail == null) {
                return null;
            }
            Node<T> cur = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                cur.last = null;
                tail.next = null;
            }
            return cur.val;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    /**
     * 双向链表实现栈
     *
     * @param <T>
     */
    public static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            this.queue = new DoubleEndsQueue<T>();
        }

        // 入栈
        public void push(T value) {
            queue.addFromHead(value);
        }

        // 出栈
        public T pop() {
            return queue.popFromHead();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /**
     * 双向链表实现队列
     *
     * @param <T>
     */
    public static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            this.queue = new DoubleEndsQueue<T>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            return queue.popFromTail();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }

    public static void main(String[] args) {
        int testTimes = 100000;
        int value = 10000;          // 生成随机数范围
        int oneTestDataNum = 100;   // 栈队列读写次数

        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            Stack<Integer> stack = new Stack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);       // [0,value]随机整数
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) { //50概率入栈
                        myStack.push(nums);
                        stack.push(nums);
                    } else {          // 50 概率出栈
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("出错了！");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) { //50概率入栈
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {          // 50 概率出栈
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("出错了！");
                        }
                    }
                }
            }
        }
        System.out.println("end...");
    }


}
