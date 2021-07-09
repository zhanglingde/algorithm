package class03;

/**
 * 小和问题
 *
 * @author zhangling 2021/7/9 13:17
 */
public class SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int M = L + ((R - L) >> 1);

        return process(arr, L, M)
                + process(arr, M + 1, R)
                + merge(arr, L, M, R);
    }

    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return res;
    }

    // 双重循环遍历，作对数器用
    public static int comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                res += arr[j] < arr[i] ? arr[j] : 0;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(100, 100);
            int[] clone = arr.clone();
            comparator(clone);
            smallSum(arr);
            if (!isEqual(arr, clone)) {
                System.out.println("error...");
                break;
            }
        }
        System.out.println("end...");

    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static boolean isEqual(int[] arr, int[] arr2) {
        if (arr == null && arr2 == null) {
            return true;
        }
        if (arr == null && arr2 != null) {
            return false;
        }
        if (arr != null && arr2 == null) {
            return false;
        }
        if (arr.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
