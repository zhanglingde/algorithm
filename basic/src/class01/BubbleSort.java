package class01;

import java.util.Arrays;

/**
 * @author zhangling 2021/7/13 17:52
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int i = 0; N > 0; N--) {
            for (int j = 0; j < N - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void bubbleSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--) {
            // 0~end 进行比较
            // 0 1   1 2  2 3   3 4  end-1 end
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {45, 79, 22, 51, 41, 38, 9, 45, 65, 71, 44, 34, 53, 78, 17, 73, 5, 87, 14, 65, 60, 22, 74, 59, 15, 81, 85};
        bubbleSort3(arr);
        printArray(arr);

        //int testTimes = 500000;
        //for (int i = 0; i < testTimes; i++) {
        //    int[] arr1 = generateRandomArray(100, 100);
        //    int[] arr2 = arr1.clone();
        //    int[] arr3 = arr1.clone();
        //    //bubbleSort(arr1);
        //    bubbleSort3(arr1);
        //    Arrays.sort(arr2);
        //    if (!isEqual(arr1, arr2)) {
        //        printArray(arr3);
        //        System.out.println("error...");
        //        break;
        //    }
        //}
        //
        //System.out.println("end...");
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
