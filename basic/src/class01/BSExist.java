package class01;

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
            }else if(sortedArr[mid] > num){
                R = mid -1;
            }else{
                L = mid +1;
            }
        }
        return sortedArr[L] == num;
    }
}
