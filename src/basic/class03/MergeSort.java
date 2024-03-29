package basic.class03;


import java.util.Arrays;

/**
 * 归并排序
 *
 * @author zhangling 2021/7/9 9:39
 */
public class MergeSort {

    // 使用递归实现
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    // 使用非递归实现
    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int mergeSize = 1;
        int N = arr.length;
        while (mergeSize < N) {
            int L = 0; // 当前左组的第一个位置
            while (L < N) {
                if (N - L <= mergeSize) {  // 右组没有数了,不需要合并，左组已经是有序的
                    break;
                }
                int M = L + mergeSize - 1;
                int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            // 防止溢出
            if (mergeSize > N / 2) {
                break;
            }
            // mergeSize * 2
            mergeSize <<= 1;
        }
    }

    public static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = L + ((R - L) >> 1);
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

    // arr[L...R]， L...M有序，  M+1...R有序， 合并使L...R有序
    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }

    public static void main(String[] args) {
        //int[] array = {71, 11, 46, 15, 53, 69, 16, 98, 91, 90};
        //mergeSort2(array);
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(100, 20);
            int[] clone = arr.clone();
            int[] arrs = arr.clone();
            mergeSort2(arr);
            Arrays.sort(clone);
            if (!isEqual(arr, clone)) {
                printArray(arrs);
                System.out.println("error...");
                break;
            }

        }
        System.out.println("end...");


    }

    public static int[] generateRandomArray(int maxValue, int maxSize) {
        int length = (int) (Math.random() * maxSize + 1);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
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
}
