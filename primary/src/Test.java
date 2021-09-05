import com.sun.source.tree.TryTree;

import java.util.*;

/**
 * @author zhangling 2021/2/6 11:20
 */
public class Test {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return;
        }
        process(arr, 0, arr.length - 1);

    }

    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int M = (L + R) / 2;
        process(arr, L, M);
        process(arr, M + 1, R);
        merge(arr, L, M, R);
    }

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
        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }

    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ 1
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ n-1
        // 1 ~ n-1
        for (int i = 0; i < arr.length; i++) {
            int minValueIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[minValueIndex] > arr[j] ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        // 0 ~ 1
        // 0 ~ 2
        // 0 ~ N
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0; pre--) {
                if (arr[pre] > arr[pre + 1]) {
                    swap(arr, pre, pre + 1);
                }
            }
        }
    }

    public static int[] parttion(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --moreL);
            } else {
                index++;
            }
        }
        swap(arr, moreL, R);
        return new int[]{lessR + 1, moreL};
    }

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equals = parttion(arr, L, R);
        process2(arr, L, equals[0] - 1);
        process2(arr, equals[1] + 1, R);
    }


    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // int[] arr = {24, 4, 3, 6, 1, 7, 4, 6, 8, 89};
        // int[] arr2 = arr.clone();
        // mergeSort(arr);
        // bubbleSort(arr);
        // selectSort(arr);


        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(50, 100);
            int[] arr2 = arr.clone();
            quickSort(arr);
            Arrays.sort(arr2);
            if (!isEqual(arr, arr2)) {
                System.out.println("error...");
                return;
            }
        }
        System.out.println("end...");
        // print(arr);
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] generateRandomArray(int maxValue, int maxLen) {
        int[] arr = new int[maxLen];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr2 == null && arr1 != null)) {
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
                System.out.println("error...");
                return false;
            }
        }
        return true;
    }
}
