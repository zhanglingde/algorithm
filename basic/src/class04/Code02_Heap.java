package class04;

import java.util.Comparator;

/**
 * 堆结构实现
 *
 * @author zhangling 2021/7/12 10:06
 */
public class Code02_Heap {

    /**
     * 大根堆的实现
     */
    public static class MyMaxHeap {
        public int[] heap;
        private int heapSize;
        private final int limit;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }

        // 随机加入一个数，使数组保持大根堆结构 （）
        public void push(int value) {
            if (heapSize == limit) {
                throw new RuntimeException("heap is full...");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }

        // 返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆结构
        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;

        }

        /**
         * 新加进来的数，依次与父节点比较
         * 当移动到 0 位置或 比父节点小时停止移动
         *
         * @param arr
         * @param index 新加进来的数索引位置
         */
        private void heapInsert(int[] arr, int index) {
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        // 从index位置，与子节点比较，不断下沉
        // 当已经没有子节点了，或子节点都比 index 小了停止操作
        private void heapify(int[] arr, int index, int heapSize) {
            int left = 2 * index + 1;
            while (left < heapSize) {   // 左孩子存在
                // 如果右节点存在，把较大孩子的下标给 largest
                int largest = (left + 1 < heapSize && arr[left + 1] > arr[left]) ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index) {
                    break;
                }
                // index 和较大孩子互换
                swap(arr, index, largest);
                index = largest;
                left = index * 2 + 1;
            }
        }


    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 每次取出数组中最大数，并把数组长度减一，用作对数器
    public static class RightMaxHeap {
        public int[] arr;
        private final int limit;
        private int size;

        public RightMaxHeap(int limit) {
            arr = new int[limit];
            this.limit = limit;
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == limit;
        }

        public void push(int value) {
            if (size == limit) {
                throw new RuntimeException("heap is full");
            }
            arr[size++] = value;
        }

        public int pop() {
            int maxIndex = 0;
            for (int i = 1; i < size; i++) {
                if (arr[i] > arr[maxIndex]) {
                    maxIndex = i;
                }
            }
            int ans = arr[maxIndex];
            //swap(arr, maxIndex, --size);


            arr[maxIndex] = arr[--size];
            return ans;
        }

    }

    public static void main(String[] args) {
        MyMaxHeap heap = new MyMaxHeap(10);
        RightMaxHeap rightMaxHeap = new RightMaxHeap(10);
        heap.push(827);
        heap.push(759);
        heap.push(760);
        heap.push(313);
        heap.push(679);
        heap.push(47);
        //rightMaxHeap.push(3);
        //rightMaxHeap.push(1);
        while (!heap.isEmpty()) {
            System.out.println("rightMaxHeap.pop() = " + heap.pop());
        }

        //int value = 1000;
        //int limit = 100;
        //int testTimes = 1000000;
        //for (int i = 0; i < testTimes; i++) {
        //    int curLimit = (int) (Math.random() * limit) + 1;
        //    MyMaxHeap my = new MyMaxHeap(curLimit);
        //    RightMaxHeap test = new RightMaxHeap(curLimit);
        //    int curOpTimes = (int) (Math.random() * limit);
        //    for (int j = 0; j < curOpTimes; j++) {
        //        if (my.isEmpty() != test.isEmpty()) {
        //            System.out.println("Oops!");
        //        }
        //        if (my.isFull() != test.isFull()) {
        //            System.out.println("Oops!");
        //        }
        //        if (my.isEmpty()) {
        //            int curValue = (int) (Math.random() * value);
        //            my.push(curValue);
        //            test.push(curValue);
        //        } else if (my.isFull()) {
        //            if (my.pop() != test.pop()) {
        //                System.out.println("Oops!");
        //            }
        //        } else {
        //            if (Math.random() < 0.5) {
        //                int curValue = (int) (Math.random() * value);
        //                my.push(curValue);
        //                test.push(curValue);
        //            } else {
        //                printArray(my.heap);
        //                printArray(test.arr);
        //                int myi = my.pop();
        //                int myt = test.pop();
        //                if (myi != myt) {
        //                    System.out.println("myi:"+myi+" myt:"+myt);
        //                    System.out.println("Oops!");
        //                    break;
        //                }
        //            }
        //        }
        //    }
        //}
        //System.out.println("finish!");

    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
