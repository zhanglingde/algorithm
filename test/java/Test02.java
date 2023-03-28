import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author zhangling
 * @date 2022/12/3 2:05 PM
 */
public class Test02 {
    // 有个 n 个台阶的楼梯，你每次可以选择上1个台阶，或者两个台阶。
    // 问你一共有多少总方法可以爬上去。

    public static int comb(int n) {
        int ans = 0;
        ans += process(n, ans);
        return ans;
    }

    public static int process(int n, int ans) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return process(n - 1, ans) + process(n - 2, ans);

    }

    public static void main(String[] args) {
        System.out.println("comb(1) = " + comb(1));
        System.out.println("comb(1) = " + comb(2));
        System.out.println("comb(1) = " + comb(3));
        System.out.println("comb(1) = " + comb(4));

    }


}
