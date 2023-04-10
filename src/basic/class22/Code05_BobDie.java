package basic.class22;

/**
 * 棋盘存活率
 * <p>
 * 给定 5 个参数，N，M，row，col，k
 * 表示在 N*M 的区域上，醉汉 Bob 初始在(row,col)位置
 * Bob 一共要迈出 k 步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候 Bob 只要离开 N*M 的区域，就直接死亡
 * 返回 k 步之后，Bob 还在 N*M 的区域的概率
 */
public class Code05_BobDie {

    public static double livePosibility1(int row, int col, int k, int N, int M) {
        return (double) process(row, col, k, N, M) / Math.pow(4, k);
    }

    /**
     * 目前在row，col位置，还有rest步要走，走完了如果还在棋盘中就获得1个生存点，返回总的生存点数
     * @param row 开始位置（row,col）
     * @param col
     * @param rest 还剩 rest 步要走
     * @param N 目标位置（N,M）
     * @param M
     * @return 总的生存点数
     */
    public static long process(int row, int col, int rest, int N, int M) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 还在棋盘中！
        if (rest == 0) {
            return 1;
        }
        // 还在棋盘中！还有步数要走
        long up = process(row - 1, col, rest - 1, N, M);
        long down = process(row + 1, col, rest - 1, N, M);
        long left = process(row, col - 1, rest - 1, N, M);
        long right = process(row, col + 1, rest - 1, N, M);
        return up + down + left + right;
    }

    // ===========动态规划=======================

    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        // 1. dp 缓存表默认设置为 1，只要还在棋盘中就是一种存活的情况
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        // 2. 计算缓存表 dp，dp 表示在某个位置还剩多少步到 目标位置的存活率
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        // 3. 存活情况数 / 总情况数 4^k
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        // 不在棋盘中返回 0
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        System.out.println(livePosibility1(6, 6, 10, 50, 50));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }

}
