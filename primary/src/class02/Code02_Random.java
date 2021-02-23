package class02;

import java.util.Random;

/**
 * 随机函数
 *
 * @author zhangling 2021/2/7 17:00
 */
public class Code02_Random {
    public static void main(String[] args) {
        double random = Math.random();

        int testTimes = 1000000;
        int count = 0;
        // 计算概率（等概率）
        for (int i = 0; i < testTimes; i++) {
            if (Math.random() < 0.3) {
                count++;
            }
        }
        System.out.println((double) count / (double) testTimes);


        int K = 9;
        // 整数范围【0，8】


        Random rand = new Random();
        rand.nextInt(9);

        int[] counts = new int[12];
        for (int i = 0; i < testTimes; i++) {
            //int ans = (int) (Math.random() * K);
            int ans = rand.nextInt(9);
            counts[ans]++;
        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i+"出现的次数 是"+counts[i]);
        }



    }
}
