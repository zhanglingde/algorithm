package basic.class20;

public class Code01_Knapsack {

    /**
     * 为了方便，没有负数
     * @param w 重量数组
     * @param v 价值数组
     * @param bag 背包容量
     * @return 不超重的情况下，背包能装的货物的最大价值
     */
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process(w, v, 0, bag);
    }

    /**
     *
     * @param w 重量数组
     * @param v 价值数组
     * @param index 当前索引
     * @param rest 背包还剩多少容量
     * @return 背包能装的最大价值
     */
    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process(w, v, index + 1, rest);
        int p2 = 0;
        int next = process(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

    /**
     * 背包问题动态规划
     *
     * @param w 重量数组
     * @param v 价值数组
     * @param bag 背包容量
     * @return 背包能装的货物最大价值
     */
    public static int dp(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];
        // 1. 从最后一行开始往上填 dp 表
        for (int index = N - 1; index >= 0; index--) {
            // 2. 从左往右填 dp 表（）
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (next != -1) {
                    p2 = v[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }

}