package basic.class20;

/**
 * 最长公共子序列 （样本对应模型）
 */
// 这个问题leetcode上可以直接测
// 链接：https://leetcode.com/problems/longest-common-subsequence/
public class Code04_LongestCommonSubsequence {

    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        // 尝试
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    /**
     * str1[0...i]和str2[0...j]，这个范围上最长公共子序列长度是多少？
     * <p>
     * 可能性分类:
     * a) 最长公共子序列，不以 str1[i] 字符结尾、也不以 str2[j] 字符结尾  >> 最长公共子序列 = str1[0...i-1] 与 str2[0...j-1] 的最长公共子序列长度(后续递归)
     * b) 最长公共子序列，以str1[i] 字符结尾、不以 str2[j] 字符结尾  >> 最长公共子序列 = str1[0...i] 与 str2[0...j-1] 的最长公共子序列长度(后续递归)
     * c) 最长公共子序列，不以str1[i] 字符结尾、以 str2[j] 字符结尾 >> 最长公共子序列 = str1[0...i-1] 与 str2[0...j] 的最长公共子序列长度(后续递归)
     * d) 最长公共子序列，以 str1[i] 字符结尾、以 str2[j] 字符结尾 >> 最长公共子序列总长度 = str1[0...i-1] 与 str2[0...j-1] 的最长公共子序列长度(后续递归) + 1(共同的结尾,str1[i] == str2[j])
     * 注意：a)、b)、c)、d)并不是完全互斥的，他们可能会有重叠的情况
     * <p>
     * b）和 c) 的范围包括了 a) 的范围；所以 a) 不参与比较
     *
     * @param str1
     * @param str2
     * @param i    str1 的索引，从右往左
     * @param j    str2 的索引，从右往左
     * @return
     */
    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) {
            // str1[0..0] 和 str2[0..0]，都只剩一个字符了
            return str1[i] == str2[j] ? 1 : 0;
        } else if (i == 0) {
            // str1[0...0] 和 str2[0...j]，str1 只剩 1 个字符了，但是 str2 不只一个字符
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                // 递归
                return process1(str1, str2, i, j - 1);
            }
        } else if (j == 0) {
            // str1[0...i] 和 str2[0...0]，str2 只剩 1 个字符了，但是 str1 不只一个字符
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                return process1(str1, str2, i - 1, j);
            }
        } else { // i != 0 && j != 0
            // p1 就是可能性 c)     完全不考虑 i，考虑 j
            int p1 = process1(str1, str2, i - 1, j);
            // p2 就是可能性 b)
            int p2 = process1(str1, str2, i, j - 1);
            // p3 就是可能性 d)，如果可能性 d)存在，即 str1[i] == str2[j]，那么 p3 就求出来，参与pk
            int p3 = str1[i] == str2[j] ? (1 + process1(str1, str2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }
    }

    // =================== 动态规划 =================================

    /**
     * 假设 str1[0...i] str2[0...j]
     * <p>
     * a) 最长公共子序列，不以 str1[i] 字符结尾、也不以 str2[j] 字符结尾  >> dp[i-1]dp[j-1]
     * b) 最长公共子序列，以str1[i] 字符结尾、不以 str2[j] 字符结尾 >> dp[i][j-1]
     * c) 最长公共子序列，不以str1[i] 字符结尾、以 str2[j] 字符结尾 >> dp[i-1][j]
     * d) 最长公共子序列，以 str1[i] 字符结尾、以 str2[j] 字符结尾 >> dp[i][j]
     *
     * @param s1
     * @param s2
     * @return
     */
    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M = str2.length;
        int[][] dp = new int[N][M];
        // dp[0][0] 位置
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // 1. 填充第 0 行
        for (int j = 1; j < M; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        // 2. 填充第 0 列
        for (int i = 1; i < N; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        // 3. 从上往下，从左往右计算 dp 表其他位置
        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                int p1 = dp[i - 1][j];
                int p2 = dp[i][j - 1];
                int p3 = str1[i] == str2[j] ? (1 + dp[i - 1][j - 1]) : 0;
                dp[i][j] = Math.max(p1, Math.max(p2, p3));
            }
        }
        return dp[N - 1][M - 1];
    }

}