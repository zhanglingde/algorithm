package class02;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现栈
 * @author zhangling 2021/7/7 16:07
 */
public class TwoQueuesImplementStack {

    public static class TwoQueuesStack<T>{
        public Queue<T> queue;
        public Queue<T> help;

        public TwoQueuesStack() {
            queue = new LinkedList<>();
            help = new LinkedList<>();
        }

        public void push(T value) {
            queue.offer(value);
        }

        /**
         * 从栈顶取一个元素，同时从栈中移除该元素
         * @return
         */
        public T poll() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            queue = help;
            help = tmp;
            return ans;
        }

        public T peek() {
            while (queue.size() > 1) {
                help.offer(queue.poll());
            }
            T ans = queue.poll();
            Queue<T> tmp = queue;
            help.offer(queue.poll());
            queue = help;
            help = tmp;
            return ans;
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }

    }

    public static void main(String[] args) {
        TwoQueuesStack<Integer> queue = new TwoQueuesStack<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        queue.push(5);
        while (!queue.isEmpty()) {
            System.out.println("queue.poll() = " + queue.poll());
        }

    }
}
