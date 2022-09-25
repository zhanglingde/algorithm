package basic.class01;

/**
 * 二分法
 *
 * @author zhangling  2021/7/5 21:31
 */
public class BSExist {

    /**
     * 判断一个数在有序数组中是否存在
     *
     * @param sortedArr 有序数组
     * @param num
     * @return
     */
    public static boolean exist(int[] sortedArr, int num) {
        if (sortedArr == null || sortedArr.length == 0) {
            return false;
        }
        int L = 0;
        int R = sortedArr.length - 1;
        int mid = 0;
        // L...R
        while (L < R) {
            // mid = (L+R)/2;
            // 可能会越界 L = 10亿 R 18 亿   L+R 超过了Integer的最大值
            // mid = L + (R-L)/2;
            mid = L + ((R - L) >> 1);
            if (sortedArr[mid] == num) {
                return true;
            } else if (sortedArr[mid] > num) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return sortedArr[L] == num;
    }

    // 找到最右侧 <= num 的数
    public static int mostRightLessNumIndex(int[] arr, int num) {
        if (arr == null && arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;        // 〈= num 的数最右位置索引
        while (L <= R) {
            int mid = L + (R - L) / 2;

            if (arr[mid] <= num) {   // 查找右侧是否还有 <= num 的数
                ans = mid;
                L = mid + 1;
            } else if (arr[mid] > num) {
                R = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,3,3,4,4,4,5,5,5,5,6,6,6,7,8,9};
        int index = mostRightLessNumIndex(arr, 3);
        System.out.println("index = " + index);


    }
}
