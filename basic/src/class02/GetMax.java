package class02;

/**
 * 递归求数组中的最大值
 *
 * @author zhangling 2021/7/8 9:55
 */
public class GetMax {

    // 求 arr 中最大值
    public static int getMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return process(arr, 0, arr.length - 1);
    }

    // 求 arr[L...R] 范围上的最大值
    private static int process(int[] arr, int L, int R) {

        if (L == R) {
            return arr[L];
        }
        int mid = L + (R - L) / 2;
        int leftMax = process(arr, L, mid);
        int rightMax = process(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);

    }

    public static void main(String[] args) {
        int[] arr = {4, 6, 3, 7};
        int max = getMax(arr);
        System.out.println("max = " + max);
        System.out.println(1/2);
    }
}
