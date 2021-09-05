package test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.function.BinaryOperator;

/**
 * @author zhangling 2021/7/9 15:41
 */
public class HashMapDemo {
    public static void main(String[] args) {
        HashMap<Integer, BigDecimal> map = new HashMap<>();
        map.put(1, BigDecimal.ONE);
        BigDecimal bigDecimal = map.get(1)
                .add(null);
        System.out.println("bigDecimal = " + bigDecimal);
    }
}
