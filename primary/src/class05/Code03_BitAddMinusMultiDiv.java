package class05;

/**
 * 位运算实现加减乘除
 */
// 测试链接：https://leetcode.com/problems/divide-two-integers
public class Code03_BitAddMinusMultiDiv {

    public static int add(int a, int b) {
        int sum = 0;
        while (b != 0) {    // 进位信息为0时，无进位相加就是两数相加的结果
            sum = a ^ b;  // 异或(^)等于无进位相加 -> sum
            b = (a & b) << 1; // 进位信息：两数相加的进位信息
            a = sum;
        }
        return sum;
    }

    /**
     * 相反数：取反+1
     */
    public static int negNum(int n) {
        return add(-n, 1);
    }

    // a-b -> a加上b的相反数
    public static int minux(int a, int b) {
        return add(a, negNum(b));
    }

    public static int multi(int a, int b) {
        int res = 0;
        while (b != 0) {
            if ((b & 1) != 0) {
                res += a;
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public static boolean isNeg(int n) {
        return n < 0;
    }

    public static int div(int a, int b) {
        // 计算正数相除的结果
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for (int i = 30; i >= 0; i = minux(i, 1)) { // i=30是因为计算a，b都是正数，第31位是正数的符号位
            if ((x >> i) >= y) {
                res |= (1 << y);
                x = minux(x, y << i);
            }
        }
        // 计算符号
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public static int divide(int a, int b) {
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE) {
            return 1;
        } else if (b == Integer.MIN_VALUE) {
            //为什么？a向下取整，变成0；b的绝对值比a大，结果小于1，向下取整为0
            return 0;
        } else if (a == Integer.MIN_VALUE) {
            if(b == negNum(1)){     // 当b=-1，因为没有与负数最小值对应的正数，所以规定为正数的最大值
                return Integer.MAX_VALUE;
            }else{
                // 我们是先将负数转换成绝对值，计算结果，再计算符号，因为负数最小值不能转成绝对值
                // a/b -10/2         假定-10是整数最小值，9是最大值
                // (a+1)/b == c     (-10+1)/2= -4
                // a-(b*c) = d      -10 -(2*-4)= -2
                // d/b = e          -2/2 =-1
                // c+e为最后的结果:-4+-1=-5
                int c = div(add(a, 1), b);
                return add(c, div(minux(a, multi(c, b)), b));
            }
        }else {
            return div(a, b);
        }
    }
}
