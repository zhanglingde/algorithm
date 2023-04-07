import com.sun.source.tree.TryTree;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangling 2021/2/6 11:20
 */
public class Test {


    public static int number(String str) {
        if (str == null || str.length() < 1) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        // index 没到最后，但是有 0
        if (chars[index] == '0') {
            return 0;
        }
        int ways = process(chars, index + 1);
        if (index + 1 < chars.length && (chars[index] - '0') * 10 + chars[index + 1] - '0' < 27) {
            ways += process(chars, index + 2);
        }
        return ways;
    }

    public static int dp1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();

    }


    public static void main(String[] args) {

    }
}
