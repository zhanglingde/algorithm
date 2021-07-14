package class03;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Code04_QuickSort {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static int[] parttion(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {      // 当前数 < 边界数，<区下一个位置 与当前数交换，<区右扩，当前数跳下一个
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) { // 当前数 > 边界数，当前数与 >区位置交换，>区左扩，当前数不变
                swap(arr, --moreL, index);
            } else {                          // 相等，当前数跳下一个位置继续比较
                index++;
            }
        }
        swap(arr, index, R);
        return new int[]{lessR + 1, moreL};

    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equals = parttion(arr, L, R);
        process(arr, L, equals[0] - 1);
        process(arr, equals[1] + 1, R);
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 = generateRandomArray(100, 100);
            int[] arr2 = arr1.clone();
            quickSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("error...");
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
}
