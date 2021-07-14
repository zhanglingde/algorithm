/**
 * @author zhangling 2021/7/14 13:21
 */
public class SpiltNum {

    public static void spiltNum(int[] arr) {
        int lessEqualsR = -1;
        int index = 0;
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            if (arr[i] <= arr[N - 1]) {
                swap(arr, ++lessEqualsR, i);
            }
        }
    }

    // 在一个数组中，将小于等于右边界的放左边，大于等于右边界的放右边，且左边的右边界要是原数组的右边界
    public static void spiltNum2(int[] arr) {
        int lessEqualsR = -1;
        int index = 0;
        int N = arr.length;
        while (index < N) {
            if (arr[index] <= arr[N - 1]) {
                swap(arr, ++lessEqualsR, index++);
            } else {
                index++;
            }
        }
    }
    // 以右边界数作边界p，将数组分成三层，最后为<p,=p,>p的顺序的数组（每层内的数无序）
    public static void spiltNum3(int[] arr) {
        int lessR = -1;
        int index = 0;
        int N = arr.length;
        int moreL = N - 1;
        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, --moreL, index);

            } else {
                index++;
            }
        }
        swap(arr, index, N - 1);

    }



    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 6, 9, 7, 6, 2, 8, 2, 7};
        spiltNum3(arr);
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


}
