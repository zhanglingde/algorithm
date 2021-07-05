package class02;

/**
 * @author zhangling 2021/2/7 15:46
 */
public class Code01_PreSum {

    /**
     * arr[L...R] 数组和
     * 使用遍历的方式计算
     */
    public static class RangeSum1 {
        private int[] arr;

        public RangeSum1(int[] array) {
            arr = array;
        }

        public int rangeSum(int L, int R) {
            int sum = 0;
            for (int i = L; i <= R; i++) {
                sum += arr[i];
            }
            return sum;
        }
    }


    public static class RangeSum2 {

        private int[] preSum;

        /**
         * 生成前缀和数组
         */
        public RangeSum2(int[] array) {
            int N = array.length;
            preSum = new int[N];
            preSum[0] = array[0];
            // 生成前缀和数组
            for (int i = 1; i < N; i++) {
                preSum[i] = preSum[i - 1] + array[i];
            }
        }

        /**
         * 计算arr[L...R] 范围上数的和
         */
        public int rangeSum(int L, int R) {
            return L == 0 ? preSum[0] : preSum[R] - preSum[L - 1];
        }

    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 6, 9, 1, 6, 7};
        RangeSum2 sum2 = new RangeSum2(arr);

        System.out.println(sum2.rangeSum(1, 5));
    }
}
