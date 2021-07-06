/**
 * @author zhangling  2021/7/5 19:41
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {6, 5, 8, 2, 9, 2, 1};
        print(arr);
        insertSort(arr);
        print(arr);
    }

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~0 有序
        // 0~1 有序
        // 0~n 有序
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
            }
            print(arr);
        }
    }

    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~n-1
        // 0~n-2
        int N = arr.length;
        for (int i = 0; N > 0; N--) {
            for (int j = 0; j < N - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            print(arr);
        }
    }

    public static void bubbleSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0~n-1
        // 0~n-2
        int N = arr.length;
        for (int i = 0; i < N; i++) {

        }

    }

    /**
     * 交换位置
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
