package class02;

/**
 * @author zhangling  2021/3/11 20:16
 */
public class Code03_RandToRand {

    /**
     * 使用此函数求 等概率返回 0~6
     *
     * @return 等概率返回 1~5
     */
    public static int f() {
        return (int) (Math.random() * 5) + 1;
    }

    /**
     * 1. 使用f()函数造一个 等概率的 0 1生成器
     *
     * @return
     */
    public static int f1() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3); // 等于3就重做
        return ans < 3 ? 0 : 1;
    }

    /**
     * 2. 判断0~6需要几位二进制可以表示
     */
    public static int g() {
        int ans = 0;
        do {
            ans = f1() << 2 + f1() << 1 + f1();  // 等概率返回0~7   111表示范围是7个数
        } while (ans == 7);
        return ans;
    }

    /**
     * 等概率返回1~7
     *
     * @return
     */
    public static int b() {
        return g() + 1;
    }

    /**
     * 只知道 x() 以固定概率返回0和1,但是x内容看不到
     * 返回0，1概率分别为 p 1-p,求等概率返回0，1
     *
     * @return
     */
    public static int x() {
        return Math.random() < 0.84 ? 0 : 1;
    }

    /**
     * @return 等概率返回 0 1
     */
    public static int y() {
        int ans = 0;
        do {
            ans = x();          // 四种情况 01 ，10，00，11 其中01和10产生的概率是相等的，其他情况重做
        } while (ans == x());
        return ans;
    }

}
