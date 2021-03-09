package class08;

/**
 * 归并排序
 */
public class Code02_MergeSort {

    // 递归方法实现
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int L, int R) {

        if (L == R) {
            return;
        }

        int mid = (L + R) / 2;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    // 合并分组
    private static void merge(int[] arr, int L, int M, int R) {

        int[] help = new int[R - L + 1];   // 添加一个辅助数组
        int i = 0;
        int p1 = L;
        int p2 = M + 1;

        // 两组都没有越界
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) { // p2越界，把p1数组元素加到help
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {  // 把元素赋值回原数组
            arr[L + i] = help[i];
        }

    }

    // 不使用递归，归并（找边界）
    public static void mergeSort2(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        int step = 1;    // 步长
        int N = arr.length;
        while (step < N) {  // 步长小于数组长度就继续 分组合并

            int L = 0;      // 找每一组的边界（左组，右组）
            while (L < N) {  // 后面还有分组

                int M = 0;
                if (N - L >= step) {    // 左组数量够
                    M = L + step - 1;
                } else {
                    M = N - 1;
                }
                if (M == N - 1) {       // 右组没有数了
                    break;
                }

                int R = 0;           // 右组有数
                if (N - M - 1 >= step) {  // 右组有足够的数
                    R = M + step;
                } else {
                    R = N - 1;
                }

                merge(arr, L, M, R);
                if (R == N - 1) {  // 合并完一组后，移动到下一组合并（左组，右组）
                    break;
                } else {
                    L = R + 1;
                }
            }
            if (step > N / 2) { // 下一次步长越界直接返回
                break;
            }
            step *= 2;  // 运行完一次后移动步长
        }

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
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort1(arr1);
            mergeSort2(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");
    }

}
