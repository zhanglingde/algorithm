package basic.class19_dp;


/**
 * 假设有排成一行的 N 个位置，记为 1~N，N 一定大于或等于 2
 * 开始时机器人在其中的 M 位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到 1 位置，那么下一步只能往右来到 2 位置；
 * 如果机器人来到 N 位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到 P 位置(P 也是 1~N 中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class Code01_RobotWalk {

    /**
     * 暴力递归版本，但是会有重复的递归步骤
     *
     * @param N     表示 N 个位置
     * @param start 开始位置
     * @param aim   目标位置
     * @param K     需要走几步
     * @return 返回有多少种走法
     */
    public static int ways1(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        return process1(start, K, aim, N);
    }

    /**
     *
     * @param cur 当前位置
     * @param rest 还剩几步
     * @param aim 目标位置
     * @param N 总有位置数 1~N
     * @return 返回 机器人从 cur 出发，走过rest步之后，最终停在aim的方法数，是多少？
     */
    public static int process1(int cur, int rest, int aim, int N) {
        if (rest == 0) { // 如果已经不需要走了，走完了！
            return cur == aim ? 1 : 0;
        }
        // (cur, rest)
        if (cur == 1) { // 1 -> 2
            return process1(2, rest - 1, aim, N);
        }
        // (cur, rest)
        if (cur == N) { // N-1 <- N
            return process1(N - 1, rest - 1, aim, N);
        }
        // (cur, rest)
        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
    }

    /**
     * 在暴力递归中添加 dp 二维表，相当于缓存，有重复步骤时直接从 dp 中取（从顶向下的动态规划（记忆搜索））
     *
     * @param N
     * @param start 开始位置
     * @param aim 目标位置
     * @param K 走 K 步
     * @return
     */
    public static int ways2(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        // 1. 设置默认值 -1，当递归的时候是 -1 就进行计算，如果不是 -1 之前计算过，就不再递归
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                dp[i][j] = -1;
            }
        }
        // 2. 递归计算 dp 缓存表
        // dp[cur][rest] == -1 -> process1(cur, rest)之前没算过！
        // dp[cur][rest] != -1 -> process1(cur, rest)之前算过！返回值，dp[cur][rest]
        // N+1 * K+1
        return process2(start, K, aim, N, dp);
    }

    // cur 范: 1 ~ N
    // rest 范：0 ~ K
    public static int process2(int cur, int rest, int aim, int N, int[][] dp) {
        // 1. 如果已经递归计算过，直接返回缓存表中计算过的结果
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        // 2. 之前没计算过，递归计算结果，最后结果保存到 dp 表中
        int ans = 0;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            ans = process2(2, rest - 1, aim, N, dp);
        } else if (cur == N) {
            ans = process2(N - 1, rest - 1, aim, N, dp);
        } else {
            ans = process2(cur - 1, rest - 1, aim, N, dp) + process2(cur + 1, rest - 1, aim, N, dp);
        }
        dp[cur][rest] = ans;
        return ans;

    }

    /**
     * 动态规划，直接计算 dp 结果
     *
     * @param N
     * @param start 开始位置
     * @param aim 目标位置
     * @param K 还剩 k 步
     * @return
     */
    public static int ways3(int N, int start, int aim, int K) {
        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
        for (int rest = 1; rest <= K; rest++) {
            dp[1][rest] = dp[2][rest - 1];
            for (int cur = 2; cur < N; cur++) {
                dp[cur][rest] = dp[cur - 1][rest - 1] + dp[cur + 1][rest - 1];
            }
            dp[N][rest] = dp[N - 1][rest - 1];
        }
        return dp[start][K];
    }

    public static void main(String[] args) {
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
    }

}
