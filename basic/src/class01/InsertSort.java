package class01;

/**
 * 插入排序
 *
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            for (int pre = end; pre - 1 >= 0 && arr[pre - 1] > arr[pre]; pre--) {
                swap(arr, pre, pre - 1);
            }
        }
    }

    public static void insertSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ 0 完成
        // 0 ~ 1
        // 0 ~ n
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            // end end-1  end-1 end-2     3 2   2 1   1 0
            int newNumIndex = end;              // 新的数的索引位置(相当于在数组尾部插入一个数)
            while (newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]) {
                swap(arr, newNumIndex - 1, newNumIndex);
                newNumIndex--;
            }
        }
    }



    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {45,2,7,2,2,4,90,32,14,3,6,77,3,6,13,21};
        insertSort2(arr);
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}
