package class03;

import javax.swing.event.AncestorEvent;

/**
 * 局部最小
 */
public class Code04_BSAwesome {

    public static int oneMinIndex(int[] arr) {

        // 边界条件
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if (N == 1) {
            return 0;
        }

        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[arr.length - 2] > arr[arr.length - 1]) {
            return N - 1;
        }

        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        // L...R 肯定有局部最小
        while (L < R - 1) { // L < R-1至少有三个数
            int mid = (L + R) / 2;
            if (arr[mid - 1] > arr[mid] && arr[mid] < arr[mid + 1]) {
                ans = mid;
                break;
            } else if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }

        }
        return arr[L] < arr[R] ? L : R;
    }

    /**
     * 生成随机数组，相邻数不相等
     *
     * @param maxLen   最大长度
     * @param maxValue 数组元素最大数
     * @return
     */
    public static int[] randomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * maxLen);
        int[] arr = new int[len];
        if (len > 0) {
            arr[0] = (int) (Math.random() * maxValue);
            for (int i = 1; i < len; i++) {
                do {
                    arr[i] = (int) (Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    public static boolean check(int[] arr, int minIndex) {
        if (arr.length == 0) {
            return minIndex == -1;
        }
        // 假设最小值在中间
        int left = minIndex - 1;
        int right = minIndex + 1;
        //如果越界，返回true，minIndex左边没有数，不影响；
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int[] arr = {3, 2, 3, 2, 3};
        System.out.println(oneMinIndex(arr));

        //int maxLen = 5;
        //int maxValue = 20;
        //int testTimes = 100000;
        //// printArray(randomArray(maxLen,maxValue));
        //System.out.println("测试开始");
        //for (int i = 0; i < testTimes; i++) {
        //    //int[] arr = randomArray(maxLen, maxValue);
        //    //printArray(arr);
        //    //int[] arr = {19, 12, 0, 12, 8, 7, 4, 8, 9, 2, 5, 3, 7};
        //    int ans = oneMinIndex(arr);
        //    if (!check(arr, ans)) {
        //        printArray(arr);
        //        System.out.println(ans);
        //        break;
        //    }
        //}
        //System.out.println("测试结束");
    }

}
