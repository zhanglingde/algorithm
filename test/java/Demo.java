import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhangling 2021/7/12 13:15
 */
public class Demo {



    public static void main(String[] args) {
        int i = 10;
        while (i > 0) {
            if (--i > 5) {
                System.out.println(i);
            }
        }
        System.out.println("end...");

    }
}
