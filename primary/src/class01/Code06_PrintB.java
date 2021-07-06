package class01;

/**
 * 打印整数的32位 二进制表示
 * 实际数的底层都是32位的
 *
 * @author zhangling 2021/2/2 17:04
 */
public class Code06_PrintB {

    public static void print(int num) {
        for (int i = 31; i >= 0; i--) {
            // 1 << 5  == 100000，左移一位*2
            // 1 & 1 == 1 ,其他都是0
            // 如果num = 8,与1向左移动8位后&运算，第8位的1保留下来，其他位置都是0
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // int 32位，long 64位
        int num = -1;
        print(num);

        System.out.println("================");
        int a = 4;
        int b = 12;
        print(a);
        print(b);
        System.out.println("==============");
        print(a & b);
        print(a | b);
        print(a ^ b);

        int c = 1023;
        print(c);
        print(c>>1);
        print(c>>>1);

        int d = -1023;
        print(d);
        print(d>>1);
        print(d>>>1);

        System.out.println("================");

        System.out.println("Integer.MIN_VALUE = " + Integer.MIN_VALUE);
        System.out.println("~Integer.MIN_VALUE = " + ~Integer.MIN_VALUE);
        System.out.println("~Integer.MIN_VALUE + 1 = " + (~Integer.MIN_VALUE + 1));
        print(Integer.MIN_VALUE);
        print(~Integer.MIN_VALUE);
        print((~Integer.MIN_VALUE + 1));

        System.out.println("===================");

        print(c);
        print(c<<2);
        print(d);
        print(d<<2);

    }
}
