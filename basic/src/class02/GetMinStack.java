package class02;

import java.util.Stack;

/**
 * @author zhangling  2021/7/6 21:20
 */
public class GetMinStack {

    /**
     * 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
     */
    public static class MyStack2 {
        private Stack<Integer> stackData;   // 数据栈
        private Stack<Integer> stackMin;    // 最小栈

        public MyStack2() {
            this.stackData = new Stack<Integer>();
            this.stackMin = new Stack<Integer>();
        }

        /**
         * 添加元素与最小栈栈顶元素比较，栈顶元素小添加栈顶元素，value小添加value
         * @param value
         */
        public void push(int value) {
            if (stackMin.isEmpty()) {
                stackMin.push(value);
            } else if (value < getMin()) {
                stackMin.push(value);
            } else {
                Integer newMin = stackMin.peek();
                stackMin.push(newMin);
            }
            stackData.push(value);
        }

        public int pop() {
            if (stackData.isEmpty()) {
                throw new RuntimeException("stackData is empty...");
            }
            stackMin.pop();
            return stackData.pop();
        }

        /**
         * 获得最小栈栈顶元素
         *
         * @return
         */
        public int getMin() {
            if (stackMin.isEmpty()) {
                throw new RuntimeException("stackMin is empty...");
            }
            return stackMin.peek();
        }
    }
}
