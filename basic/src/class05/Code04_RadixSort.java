package class05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 基数排序
 *
 * @author zhangling 2021/7/13 11:09
 */
public class Code04_RadixSort {

    //基数排序
    public static void radixSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int digit = maxbits(arr);
        // 设置10个桶（队列）
        Queue<Integer>[] queues = new Queue[10];
        for (int i = 0; i < queues.length; i++) {
            queues[i] = new LinkedList<>();
        }
        int index = 0;
        for (int d = 0; d < digit; d++) {
            for (int i = 0; i < arr.length; i++) {
                // 求数的个位，十位，百位作为数组的桶的下标
                index = arr[i] / (int) Math.pow(10, d) % 10;
                queues[index].offer(arr[i]);
            }
            int j = 0;
            for (int i = 0; i < queues.length; i++) {
                while (!queues[i].isEmpty()) {
                    arr[j++] = queues[i].poll();
                }
            }
        }
    }

    // 基数排序优化
    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    // arr[L...R]排序，digit:最大值的十进制位数
    public static void radixSort(int[] arr, int L, int R, int digit) {
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] help = new int[R - L + 1];
        for (int d = 1; d <= digit; d++) {
            int[] count = new int[radix];
            for (i = L; i <= R; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            // 求前缀和数组
            for (i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            for (i = R; i >= L; i--) {
                j = getDigit(arr[i], d);
                help[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    /**
     * 获得相应位数上的值
     *
     * @param num   非负数
     * @param digit 从右往左数数的位数
     * @return
     */
    public static int getDigit(int num, int digit) {
        return num / (int) Math.pow(10, digit - 1) % 10;
    }

    // 获得数组 最大值的位数
    public static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        //int[] arr = {50, 68, 62, 29, 91, 20, 91};
        //radixSort(arr);

        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(100, 50);
            int[] arr2 = arr1.clone();
            int[] arr3 = arr1.clone();
            //radixSort2(arr1);
            radixSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("error...");
                printArray(arr3);
                break;
            }
        }
        System.out.println("end...");
    }

    public static int[] generateRandomArray(int maxValue, int maxSize) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
