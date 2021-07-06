package class01;

/**
 * 异或运算
 *
 * @author zhangling 2021/7/6 10:46
 */
public class EvenTimesOddTimes {

    // 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        // 假设奇数次数为 a,b  eor = a ^ b
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        // 取出 eor 最右侧 1
        // eor :     00010010001000
        // rightOne: 00000000001000
        int rightOne = eor & (-eor);

        int onlyOne = 0; // eor'
        for (int i = 0; i < arr.length; i++) {
            // arr[1]  =   11101101100000
            // rightOne=   00000000001000
            if ((arr[i] & rightOne) != 0) { // 根据 & 的结果是否为0，将整个数组分成两类数，a，b分别在这两类数中
                onlyOne ^= arr[i];
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));


    }

    // 打印二进制1出现的次数
    public static int bit1Counts(int N) {
        int count = 0;

        while (N != 0) {
            int rightOne = N & (-N);
            count++;
            N ^= rightOne;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,1,2,3,4,4,4,4,5,5,6,6,7,9,8,8,7};
        printOddTimesNum2(arr);

        System.out.println(bit1Counts(7));
    }
}
