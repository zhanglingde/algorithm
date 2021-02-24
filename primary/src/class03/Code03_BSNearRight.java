package class03;

import java.util.EmptyStackException;

/**
 * 有序数组中找到 <= num 最右的位置
 *
 * @author zhangling 2021/2/24 9:51
 */
public class Code03_BSNearRight {

    public static int nearestIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;

        while (L <= R) {
            int mid = (L + R) / 2;
            if (arr[mid] <= num) {
                // 找到相等的数后，继续找右边一个，看有没有相等的，直到有别没有相等的数
                ans = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 7, 7};
        int index = nearestIndex(arr, 6);
        System.out.println(index);
    }
}
