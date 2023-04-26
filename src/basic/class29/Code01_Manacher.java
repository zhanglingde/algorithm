package basic.class29;

/**
 * 假设字符串 str 长度为 N，想返回最长回文子串的长度
 * 时间复杂度 O(N)
 */
public class Code01_Manacher {

    /**
     * 使用 Manacher 算法寻找给定字符串 s 的最长回文子串长度
     *
     * @param s 给定字符串
     * @return 最长回文子串长度
     */
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // 1. 将原字符串的每个相邻两个字符中间插入一个分隔符 #（"12132" -> "#1#2#1#3#2#"）
        char[] str = manacherString(s);
        // 2. pArr[i] 表示以 str[i] 为中心的最长回文半径
        int[] pArr = new int[str.length];
        int C = -1;   // 最右的扩成功位置的中心点
        // 讲述中：R 代表最右的扩成功的位置
        // coding：最右的扩成功位置的再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        // 遍历计算回文半径
        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R 第一个违规的位置，i >= R

            // 1. 如果 i 在 R 的左侧(i 在已知的最长回文子串中)，利用回文串的对称性，可以利用之前的计算结果 pArr[2*C-i] (2*C-i 就是 i')，否则 pArr[i] 至少为 1
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 2. i 不在已知的最长回文串中，利用中心扩散法计算以每个字符为中心的回文半径
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    // 节省代码，也走 上面
                    break;
                }
            }
            // 3. 更新最右的扩成功位置的再下一个位置和中心点（将 R 推代往右了，更新 R 和 C）
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            // 4. 更新最长回文半径
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    /**
     * 在原字符串的每个相邻两个字符中间插入一个分隔符 #
     *
     * @param str 原字符串 12132
     * @return 插入分隔符后的字符串 #1#2#1#3#2#
     */
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            // (i & 1) == 0 为奇数位置(i为偶数),奇数位置插入分隔符 #
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        // int possibilities = 5;
        // int strSize = 20;
        // int testTimes = 5000000;
        // System.out.println("test begin");
        // for (int i = 0; i < testTimes; i++) {
        //     String str = getRandomString(possibilities, strSize);
        //     if (manacher(str) != right(str)) {
        //         System.out.println("Oops!");
        //     }
        // }
        // System.out.println("test finish");
        manacher("adcadca");
    }

}
