package class02;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 数组实现栈和队列
 * RingBuffer 数组是有限的，当作为队列存储满后，前面的数出队了，新增加的数又存储到开始的位置
 *
 * @author zhangling 2021/7/6 16:40
 */
public class RingArray {
    /**
     * 数组实现栈
     */
    public static class MyStack {
        private int[] arr;
        private int index;

        public MyStack(int limit) {
            this.arr = new int[limit];
            this.index = 0;
        }

        public void push(int value) {
            arr[index++] = value;
        }

        public int pop() {
            return arr[--index];
        }

        public boolean isEmpty() {
            return index == 0;
        }
    }

    public static class MyQueue {
        private int[] arr;
        private int pushi;
        private int polli;
        private int size;  // size 解耦队列取元素和写元素指针的追赶关系
        private final int limit;

        public MyQueue(int limit) {
            arr = new int[limit];
            this.pushi = 0;
            this.polli = 0;
            this.size = 0;
            this.limit = limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("队列已满...");
            }
            size++;
            arr[pushi] = value;
            pushi = nextIndex(pushi);
        }

        public int poll() {
            if (size == 0) {
                throw new RuntimeException("队列为空...");
            }
            size--;
            int val = arr[polli];
            polli = nextIndex(polli);
            return val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 获取下一个索引
        private int nextIndex(int i) {
            return i == limit - 1 ? 0 : i + 1;
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
        int value = 10000;
        int max = 10;
        for (int i = 0; i < testTimes; i++) {
            MyStack myStack = new MyStack(10);
            Stack<Integer> stack = new Stack<>();
            MyQueue myQueue = new MyQueue(10);
            Queue<Integer> queue = new ConcurrentLinkedQueue<>();
            for (int j = 0; j < max; j++) {
                int num = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(num);
                    stack.push(num);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(num);
                        stack.push(num);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("error...");
                        }
                    }
                }

                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.push(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.push(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("error...");
                        }
                    }
                }

            }

        }
        System.out.println("end...");
    }
}
