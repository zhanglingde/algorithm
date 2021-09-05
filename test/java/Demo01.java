import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangling  2021/9/2 20:35
 */
public class Demo01 {
    static volatile int i = 1;
    static Thread t1;
    static Thread t2;

    public static void main(String[] args) {
        Print p = new Print(50);

        t1 = new Thread(() -> {
            for (int j = 0; j < p.loop; j++) {
                p.print(i, t2);
                i++;
            }
        });

        t2 = new Thread(() -> {
            for (int j = 0; j < p.loop; j++) {
                p.print(i, t1);
                i++;
            }
        });
        t1.start();
        t2.start();
        LockSupport.unpark(t1);
    }

}


class Print {
    int loop;

    public Print(int loop) {
        this.loop = loop;
    }

    public void print(int num, Thread thread) {
        LockSupport.park();
        System.out.println(num);

        LockSupport.unpark(thread);
    }
}



