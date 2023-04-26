package basic.class30;

/**
 * 蓄水池算法
 * <p>
 * 解决的问题：
 * 假设有一个源源吐出不同球的机器，
 * 只有装下 10 个球的袋子，每一个吐出的球，要么放入袋子，要么永远扔掉
 * 如何做到机器吐出每一个球之后，所有吐出的球都等概率被放进袋子里
 */
public class Code03_ReservoirSampling {

    public static class RandomBox {
        private int[] bag;   // 蓄水池
        private int N;       // 蓄水池的容量
        private int count;   // 当前已经加入蓄水池的数目

        public RandomBox(int capacity) {
            bag = new int[capacity];
            N = capacity;
            count = 0;
        }

        // 生成 1 ~ max 质检的随机整数
        private int rand(int max) {
            return (int) (Math.random() * max) + 1;
        }

        public void add(int num) {
            count++;
            if (count <= N) {
                // 如果蓄水池未满，直接将数加入蓄水池
                bag[count - 1] = num;
            } else {
                // 蓄水池已满，以 k/count 的概率决定是否替换蓄水池中的某个数
                if (rand(count) <= N) {
                    bag[rand(N) - 1] = num;
                }
            }
        }

        // 从蓄水池中随机抽取 k 个数，并返回它们构成的数组
        public int[] choices() {
            int[] ans = new int[N];
            for (int i = 0; i < N; i++) {
                ans[i] = bag[i];
            }
            return ans;
        }

    }

    // 请等概率返回 1~i 中的一个数字
    public static int random(int i) {
        return (int) (Math.random() * i) + 1;
    }

    public static void main(String[] args) {
        System.out.println("hello");
        int test = 10000;
        int ballNum = 17;
        int[] count = new int[ballNum + 1];
        for (int i = 0; i < test; i++) {
            int[] bag = new int[10];    // 蓄水池容量为 10
            int bagi = 0;
            for (int num = 1; num <= ballNum; num++) {
                if (num <= 10) {
                    // 1. 前 10 个球直接加入蓄水池
                    bag[bagi++] = num;
                } else { // num > 10
                    // 2. 接收到第 i 个数据时，若 i > m,在 [0...i] 范围内取随机数 d，若 d 的值落在 [0...m] 的范围内，则用接收到的第 i 个数替换蓄水池中第 d 个数
                    if (random(num) <= 10) {
                        // 生成随机数在范围内，以 10/num 的概率决定是否将第 num 个球加入蓄水池；
                        bagi = (int) (Math.random() * 10);  // 被替换的球的下标
                        bag[bagi] = num;
                    }
                }

            }
            for (int num : bag) {  // 记录每个球被选中的次数
                count[num]++;
            }
        }
        // 输出每个球被选中的次数（测试次数够多，概率会趋于一致）
        for (int i = 0; i <= ballNum; i++) {
            System.out.println(count[i]);
        }

        System.out.println("hello");
        int all = 100;
        int choose = 10;
        int testTimes = 50000;
        int[] counts = new int[all + 1];
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choices();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }
        }

        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }

    }
}
