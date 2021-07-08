package class02;

import java.util.Stack;

/**
 * 两个栈实现队列
 * @author zhangling 2021/7/7 16:05
 */
public class TwoStacksImplementQueue {

    public static class TwoStacksQueue<T>{
        private Stack<Integer> stackPush;
        private Stack<Integer> stackPop;

        public TwoStacksQueue(){
            stackPush = new Stack<>();
            stackPop = new Stack<>();
        }

        /**
         * push栈 数据倒入 pop栈
         * 1. pop栈 必须为空
         * 2. push栈中数据必须一次倒完
         */
        public void pushToPop(){
            if (stackPop.isEmpty()) {
                while (!stackPush.isEmpty()) {
                    stackPop.add(stackPush.pop());
                }
            }
        }

        public void add(int value){
            stackPush.add(value);
            pushToPop();
        }

        public int pop() {
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("queue is empty...");
            }
            pushToPop();
            return stackPop.pop();
        }

        public int peek(){
            if (stackPop.isEmpty() && stackPush.isEmpty()) {
                throw new RuntimeException("queue is empty...");
            }
            pushToPop();
            return stackPop.peek();
        }

        public boolean isEmpty() {
            return stackPop.isEmpty() && stackPush.isEmpty();
        }

    }

    public static void main(String[] args) {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.pop() = " + queue.pop());
        System.out.println("queue.pop() = " + queue.pop());
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.pop() = " + queue.pop());

    }


}
