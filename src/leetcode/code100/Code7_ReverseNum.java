package leetcode.code100;

// 整数反转
public class Code7_ReverseNum {

    public static int reverse(int x) {
        int rev = 0;
        int digit;
        while (x != 0) {
            // -2147483648  2147483647
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            // 弹出原数末位数
            digit = x % 10;
            x /= 10;
            // 将弹出数加到反转数末位
            rev = rev * 10 + digit;
        }
        return rev;
    }

    public static void main(String[] args) {
        int x = 1238764;
        System.out.println("reverse(x) = " + reverse(x));
        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
        System.out.println("(Integer.MAX_VALUE /10) = " + (Integer.MAX_VALUE / 10));
    }
}
