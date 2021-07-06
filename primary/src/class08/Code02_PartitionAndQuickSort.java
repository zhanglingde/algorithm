package class08;

/**
 * 快速排序
 */
public class Code02_PartitionAndQuickSort {

    /**
     * 在一个数组中，将小于等于右边界的放左边，大于等于右边界的放右边，且左边的右边界要是原数组的右边界
     *
     * @param arr
     */
    public static void spiltNum1(int[] arr) {
        int lessEqualsR = -1;    // 小于等于区
        int index = 0;         // 当前数
        int N = arr.length;
        while (index < N) {
            if (arr[index] <= arr[N - 1]) {
                // swap(arr, lessEquals + 1, index);
                // lessEquals++;
                // index++;
                swap(arr, ++lessEqualsR, index++);
            } else {
                index++;
            }
        }
    }

    // 以右边界数作边界p，将数组分成三层，最后为<p,=p,>p的顺序的数组（每层内的数无序）
    public static void spiltNum2(int[] arr) {
        int lessR = -1;    // 小于区域左边界
        int index = 0;
        int N = arr.length;
        int moreL = N - 1;  // 大于区域右边界
        while (index < moreL) {
            if (arr[index] < arr[N - 1]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[N - 1]) {
                swap(arr, moreL - 1, index);
                moreL--;
            } else {
                index++;
            }
        }
        swap(arr, index, N - 1);
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    /**
     * arr[L...R]范围上拿arr[R]作划分值，
     * 将数组划分成 < = >
     *
     * @return 返回等于区域的边界
     */
    public static int[] parttion(int[] arr, int L, int R) {
        int lessR = L - 1;
        int moreL = R;
        int index = L;
        while (index < moreL) {
            if (arr[index] < arr[R]) {
                swap(arr, ++lessR, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --moreL, index);
            } else {
                index++;
            }
        }
        swap(arr, moreL, R);
        return new int[]{lessR + 1, moreL};
    }

    // 使用递归实现快速排序
    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equals = parttion(arr, L, R);
        process(arr, L, equals[0] - 1);
        process(arr, equals[1] + 1, R);
    }


    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[] { -1, -1 };
        }
        if (L == R) {
            return new int[] { L, R };
        }
        int less = L - 1;
        int more = R;
        int index = L;
        while (index < more) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] < arr[R]) {
                swap(arr, index++, ++less);
            } else {
                swap(arr, index, --more);
            }
        }
        swap(arr, more, R); // <[R] =[R] >[R]
        return new int[] { less + 1, more };
    }




    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//		int[] arr = { 7, 1, 3, 5, 4, 5, 1, 4, 2, 4, 2, 4 };
//
//		splitNum2(arr);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.print(arr[i] + " ");
//		}

        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            quickSort1(arr1);
            // quickSort2(arr2);
            quickSort3(arr3);
            if (!isEqual(arr1, arr3)) {
                System.out.println("Oops!");
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println(succeed ? "Nice!" : "Oops!");

    }
}
