import java.util.Date;
import java.util.HashMap;

/**
 * @author zhangling 2021/7/6 13:27
 */
public class DoubleNode {
    public int value;
    public DoubleNode last;
    public DoubleNode next;

    public DoubleNode(int val) {
        this.value = val;
    }

    public static void main(String[] args) {
        HashMap<String, Date> map = new HashMap<>();
        map.put("a", null);
        map.put("b", null);
    }
}
