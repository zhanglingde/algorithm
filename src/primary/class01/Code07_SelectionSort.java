package primary.class01;

/**
 * 选择排序
 *
 * @author zhangling 2021/2/3 11:49
 */
public class Code07_SelectionSort {

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) { // 先找边界条件
            return;
        }
        // 0~n-1 取出最小值
        // 1~n-1 取出最小值
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            int minValueIndex = i;         // 最小值索引作交换用
            for (int j = i + 1; j < N; j++) {         // 找到最小值的索引
                minValueIndex = arr[minValueIndex] > arr[j] ? j : minValueIndex;
            }
            swap(arr, minValueIndex, i);
        }

    }

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ n-1
        // 0 ~ n-2
        // 0 ~ end
        int N = arr.length;
        for (int end = N - 1; end >= 0; end--) {
            //0 ~ end 进行比较
            // 0 1    1 2    2 3   3 4      end-1 end
            for (int second = 1; second <= end; second++) {
                if (arr[second - 1] > arr[second]) {
                    swap(arr, second - 1, second);
                }
            }
        }

    }

    /**
     * 插入排序
     * @param arr
     */
    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ 0 完成
        // 0 ~ 1
        // 0 ~ n
        int N = arr.length;
        for (int end = 1; end < N; end++) {
            // end end-1  end-1 end-2     3 2   2 1   1 0
            for (int pre = end - 1; pre >= 0 && arr[pre] > arr[pre + 1]; pre--) {
                swap(arr, pre, pre + 1);
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
            int newNumIndex = end;              // 新的数的索引位置
            while (end - 1 >= 0 && arr[end - 1] > arr[end]) {
                swap(arr, end - 1, end);
                newNumIndex--;
            }
        }
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 2, 3, 5, 1, 3, 6, 8, 5};
        printArray(arr);
        insertSort1(arr);
        printArray(arr);
    }
}
