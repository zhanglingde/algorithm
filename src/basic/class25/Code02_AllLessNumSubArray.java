package basic.class25;

import java.util.LinkedList;

/**
 * 给定⼀个整型数组 arr，和⼀个整数 num
 * 某个 arr 中的⼦数组 sub，如果想达标，
 * 必须满⾜： sub 中最⼤值 – sub 中最⼩值 <= num
 * 返回 arr 中达标⼦数组的数量
 */
public class Code02_AllLessNumSubArray {

    // 暴力的对数器方法   O(N^3)
    public static int right(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        // 1. 递归遍历 arr 中所有子数组
        for (int L = 0; L < N; L++) {
            for (int R = L; R < N; R++) {
                int max = arr[L];
                int min = arr[L];
                // 2. 遍历子数组，找到子数组中最大值和最小值
                for (int i = L + 1; i <= R; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }
        return count;
    }

    // ==================== 滑动窗口 ========================================

    // O(N)
    public static int num(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            // 1. 该循环，右窗口右移，当不满足条件 （sub 中最⼤值 – sub 中最⼩值 <= num） 时退出循环
            while (R < N) {
                // 最大窗口队列不为空，且队列最后一个元素 <= arr[R](即当队列中有更小的值时，队列的元素都出队列，队列中第一个元素一定是最大值)
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                // 窗口右移，且元素下标进入队列
                maxWindow.addLast(R);
                // 最小窗口队列不为空，且队列最后一个元素 >= arr[R];（队列中第一个元素一定是最小值）
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                // 窗口右移，且元素下标进入队列
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            // 2. 左窗口不动，右窗口右移计算有多少个满足条件的子数组
            count += R - L;
            // 3. 左窗口要右移了，如果最大值窗口与最小值窗口是左窗口的值，则窗口中的值出队列
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
        }
        return count;
    }

    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 3, 11, 0, 23, 15};
        System.out.println("num(arr, 3) = " + num(arr, 3));

        // int maxLen = 100;
        // int maxValue = 200;
        // int testTime = 100000;
        // System.out.println("测试开始");
        // for (int i = 0; i < testTime; i++) {
        //     int[] arr = generateRandomArray(maxLen, maxValue);
        //     int sum = (int) (Math.random() * (maxValue + 1));
        //     int ans1 = right(arr, sum);
        //     int ans2 = num(arr, sum);
        //     if (ans1 != ans2) {
        //         System.out.println("Oops!");
        //         printArray(arr);
        //         System.out.println(sum);
        //         System.out.println(ans1);
        //         System.out.println(ans2);
        //         break;
        //     }
        // }
        // System.out.println("测试结束");

    }

}