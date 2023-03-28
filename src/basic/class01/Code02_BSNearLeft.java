package basic.class01;

/**
 * 有序数组中找到 >= num 最左的位置
 *
 */
public class Code02_BSNearLeft {

    //有序数组中找到 >= num 最左的位置
    public static int mostLeftNoLessNumIndex(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;   // >=num 的数最左边位置索引

        while (L <= R) {
            int mid = (L + R) / 2;

            if (arr[mid] >= num) {
                // 找到num后，继续找该数左边有没有相等的
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 3, 4, 4, 5, 6, 7, 7};
        int index = mostLeftNoLessNumIndex(arr, 7);
        System.out.println(index);
    }
}
