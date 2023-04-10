package basic.class23;


/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SplitNumber {

    // n为正数
    public static int ways(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return process(1, n);
    }

    /**
     * 拆分正数 pre，拆分出来的数不能比 pre 小，返回拆解的方法数
     *
     * @param pre  上一个拆出来的数
     * @param rest 还剩 rest 需要去拆
     * @return 返回拆解的方法数
     */
    public static int process(int pre, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (pre > rest) {
            return 0;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return ways;
    }

    // ================ 动态规划 ==============================

    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        // 1. dp 表对角线设置默认值 1
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;  // 第一行无用
            dp[pre][pre] = 1;
        }
        // 2.从下往上填充 dp 表( dp[pre,rest] 依赖 dp[pre,rest - pre] )
        for (int pre = n - 1; pre >= 1; pre--) {
            // 3. 从左往右填充 dp 表
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                // 存在枚举行为的动态规划（循环）
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    // ================= 动态规划 ===============================

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        // 1. dp 表对角线设置默认值 1
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;  // 第一行无用
            dp[pre][pre] = 1;
        }
        // 2.从右往左填充 dp 表( dp[pre,rest] 依赖 dp[pre,rest - pre] )
        for (int pre = n - 1; pre >= 1; pre--) {
            // 3. 从上往下填充 dp 表
            for (int rest = pre + 1; rest <= n; rest++) {
                // 优化枚举行为
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 39;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
    }

}