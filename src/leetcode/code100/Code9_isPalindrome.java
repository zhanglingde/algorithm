package leetcode.code100;

// 判断是否是回文数
public class Code9_isPalindrome {

    public static boolean isPalindrome(int num) {
        // 原数最后一位等于0，且原数非0一定不是回文
        if (num < 0 || (num % 10 == 0 && num != 0)) {
            return false;
        }
        int rev = 0;
        int digit = 0;
        while (num > rev) {
            // 弹出最后一个数
            digit = num % 10;
            num /= 10;
            // 弹出的数加到反转后的数中
            rev = rev * 10 + digit;
        }
        // 原数是奇数时，通过 rev / 10 去除以中位数
        return num == rev || num == rev / 10;
    }

    public static void main(String[] args) {
        int num = 12211;
        System.out.println("isPalindrome(num) = " + isPalindrome(num));
    }
}
