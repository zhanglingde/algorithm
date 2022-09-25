package basic.class01;

/**
 * 异或运算交换两个元素，不申请额外变量
 *
 * @author zhangling  2021/7/5 22:11
 */
public class Swap {
    public static void main(String[] args) {
        // int a = 1;
        // int b = 2;
        // // 交换两个数，使用（ N ^ N == 0; 0 ^ N == N）的结论，假设 a = 甲，b = 乙
        // a = a ^ b; // a = 甲 ^ 乙; b = 乙;
        // b = a ^ b; // a = 甲 ^ 乙(不变) ;  b = 甲 ^ 乙 ^ 乙 = 甲; （将 a = 甲 ^ 乙 代入计算）
        // a = a ^ b; // a = 甲 ^ 乙 ^ 甲 = 乙；  b = 甲(不变)；
        //
        // System.out.println("a = " + a);
        // System.out.println("b = " + b);
        int[] arr = {1, 2, 2, 3, 4, 5, 4, 5, 1};
        findOddNum(arr);
    }

    public static void findOddNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        if (arr.length == 1) {
            System.out.println(arr[0]);
        }
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num = num ^ arr[i];
        }
        System.out.println("num = " + num);
    }
}
