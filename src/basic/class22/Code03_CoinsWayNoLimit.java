package basic.class22;

/**
 * 货币面值
 *
 * arr 是面值数组，其中的值都是正数且没有重复。再给定一个正数 aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成 aim 的方法数
 *
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就 3 种方法，所以返回 3
 */
public class Code03_CoinsWayNoLimit {

    public static int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process(arr, 0, aim);
    }


    /**
     * arr[index....] 所有的面值，每一个面值都可以任意选择张数，组成正好rest这么多钱，方法数多少？
     *
     * @param arr
     * @param index 货币位置 0 ~ N
     * @param rest  距目标还剩多少 0 ~ aim
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) { // 没钱了
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        // 张数从 0 开始 +
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ways += process(arr, index + 1, rest - (zhang * arr[index]));
        }
        return ways;
    }

    public static int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        // 从下往上填 （index 依赖于底下）
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 计算一个格子需要 for 循环
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 从 dp1 严格依赖版本，观察依赖关系，一个 dp 格子的 for 循环优化为常数项时间复杂度
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinsWay(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("测试结束");
    }

}