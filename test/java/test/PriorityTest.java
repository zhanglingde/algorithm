package test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhangling 2021/7/12 11:18
 */
public class PriorityTest {
    public static void main(String[] args) {
        // 大根堆（传入比较器实现大根堆）
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(3);
        heap.add(7);
        heap.add(5);
        heap.add(8);
        heap.add(1);
        while (!heap.isEmpty()) {
            System.out.println("heap.poll() = " + heap.poll());
        }
    }

    public static class MyComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

}
