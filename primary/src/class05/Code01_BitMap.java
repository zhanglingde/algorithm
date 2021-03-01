package class05;

import java.util.HashSet;

/**
 * 位图
 */
public class Code01_BitMap {

    public static class BitMap {

        private long[] bits;

        // max最大数：可以放0-max个数
        public BitMap(int max) {
            // (max+64)>>6  ->  (max+64)/64
            // 1个数：(1+64)/64
            bits = new long[(max + 64) >> 6];
        }

        public void add(int num) {
            // num / 64相当于在数组中第几个整数，可以表示该整数可表示该数的范围
            // num%64 —> num&63（使用二进制算,只保留二进制后7位的状态）,该数在该64范围内是第几个数
            // num&63该数是在64范围中的哪个数，需要将该数标志，&上1移(num&63)位
            bits[num >> 6] |= (1L << (num & 63));
        }

        public void delete(int num) {
            //00001100 把3位置删除 -> 00001100 & 11111011
            // ~取反
            bits[num >> 6] &= ~(1L << (num & 63));
        }

        // 判断存不存在
        public boolean contains(int num) {
            return (bits[num >> 6] & (1L << (num & 63))) != 0;
        }

    }

    // 测试，分别往位图和set中加删数据，最后判断位图中存在的数据是否正确
    public static void main(String[] args) {
        System.out.println("测试开始");
        int max = 10000;
        BitMap bitMap = new BitMap(max);
        HashSet<Integer> set = new HashSet<>();
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int num = (int) (Math.random() * (max + 1));
            double decide = Math.random();
            if (decide < 0.333) {
                bitMap.add(num);
                set.add(num);
            } else if (decide < 0.666) {
                bitMap.delete(num);
                set.remove(num);
            } else {
                if (bitMap.contains(num) != set.contains(num)) {
                    System.out.println("Oops!");
                    break;
                }
            }
        }
        for (int num = 0; num <= max; num++) {
            if (bitMap.contains(num) != set.contains(num)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
