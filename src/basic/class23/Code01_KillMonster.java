package basic.class23;

/**
 * 怪兽死亡的概率
 * <p>
 * 给定 3 个参数，N，M，K
 * 怪兽有 N 滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求 K 次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {

    // =============递归======================

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        // 总情况数：（M+1）^k
        long all = (long) Math.pow(M + 1, K);
        long kill = process(K, M, N);
        return (double) ((double) kill / (double) all);
    }

    /**
     * 递归计算怪兽死亡的情况数量
     *
     * @param times 还剩多少次攻击次数
     * @param M 每次伤害在 [0~M] 上
     * @param hp 怪兽总 hp
     * @return 返回砍死的情况数！
     */
    public static long process(int times, int M, int hp) {
        if (times == 0) {
            return hp <= 0 ? 1 : 0;
        }
        if (hp <= 0) {
            // 情况数：（M+1）^k
            return (long) Math.pow(M + 1, times);
        }
        long ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(times - 1, M, hp - i);
        }
        return ways;
    }

    // ===================== 动态规划1 ===============================

    public static double dp1(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        // 1. 遍历横左坐标 攻击次数；从上往下填
        for (int times = 1; times <= K; times++) {
            // 填充第 0 列的情况数（第 0 列只有 0 位置是 1，其他都是 0）
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                long ways = 0;
                // 枚举行为优化为常数项
                for (int i = 0; i <= M; i++) {
                    if (hp - i >= 0) {
                        // 任意一个位置依赖左边一列（知道第一列的值可以往后推）
                        ways += dp[times - 1][hp - i];
                    } else {
                        // dp 表没有负数；原递归 hp 小于0时也计算
                        // hp < 0 怪兽已经死亡，还有 times - 1 次攻击机会，不管攻击伤害多少，最后都是怪兽死亡
                        ways += (long) Math.pow(M + 1, times - 1);
                    }
                }
                dp[times][hp] = ways;
            }
        }
        // 根据主函数确定变量 K N
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    // ===================== 动态规划2 ===============================

    /**
     * 将动态规划中每个 dp 表的枚举行为优化为常数项
     *
     * @param N
     * @param M 每次伤害 [0-M]
     * @param K 攻击次数
     * @return
     */
    public static double dp2(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                // dp[5][10] = dp[4][10...10-M] = dp[5][9] + dp[4][10]
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {   // 判断表示不越界
                    // dp[5][10]
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    // 怪兽已经死亡,还有攻击次数，；hp < 0 ，格子不在表中，仍然要计算
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        double pow = Math.pow(1.2, 2.3);
        System.out.println("pow = " + pow);

        // int NMax = 10;
        // int MMax = 10;
        // int KMax = 10;
        // int testTime = 200;
        // System.out.println("测试开始");
        // for (int i = 0; i < testTime; i++) {
        // 	int N = (int) (Math.random() * NMax);
        // 	int M = (int) (Math.random() * MMax);
        // 	int K = (int) (Math.random() * KMax);
        // 	double ans1 = right(N, M, K);
        // 	double ans2 = dp1(N, M, K);
        // 	double ans3 = dp2(N, M, K);
        // 	if (ans1 != ans2 || ans1 != ans3) {
        // 		System.out.println("Oops!");
        // 		break;
        // 	}
        // }
        // System.out.println("测试结束");
    }

}
