package basic.class23;

/**
 * 组成目标货币面值所需最小货币数
 * <p>
 * arr 是面值数组，其中的值都是正数且没有重复。再给定一个正数 aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成 aim 的最少货币数
 */
public class Code02_MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        return process(arr, 0, aim);
    }

    /**
     * 组成目标面值需要的最小面值张数
     *
     * @param arr 面值数组，每张面值数无限
     * @param index 索引
     * @param rest 目标面值
     * @return 返回需要面值的最小张数
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // Integer.MAX_VALUE 标记无效值
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            int ans = Integer.MAX_VALUE;
            // 1. 张数从 0 开始增加遍历
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                // 递归返回的不是 Integer.MAX_VALUE，说明是有效的（前面用了zhang 张，拼成了 zhang * arr[index] 面值，剩下的用了 next 张）
                int next = process(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    // 后续的张数 + 当前使用的
                    ans = Math.min(ans, zhang + next);
                }
            }
            return ans;
        }
    }

    // =============== 动态规划 ============================

    public static int dp1(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        // 1. dp 表设置标记值 Integer.MAX_VALUE
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 2. 从最后一行往上填 dp 表
        for (int index = N - 1; index >= 0; index--) {
            // 3. 从左往右填 dp 表
            for (int rest = 0; rest <= aim; rest++) {
                int ans = Integer.MAX_VALUE;
                // 有枚举行为的动态规划（存在循环）
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {

                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, zhang + next);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    // ==================== 动态规划2 ======================================

    public static int dp2(int[] arr, int aim) {
        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        // 1. dp 表设置标记值 Integer.MAX_VALUE
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        // 2. 从最后一行往上填 dp 表
        for (int index = N - 1; index >= 0; index--) {
            // 3. 从左往右填 dp 表
            for (int rest = 0; rest <= aim; rest++) {
                // 优化枚举行为
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
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
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = dp1(arr, aim);
            int ans3 = dp2(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}