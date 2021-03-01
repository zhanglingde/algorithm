package class03;

import java.util.HashMap;

public class Code05_HashMapTreeMap {
    public static void main(String[] args) {

        HashMap<String,String> map = new HashMap<>();

        map.put("1", "zhangsan");
        System.out.println(map.get("1"));
        map.put("1", "lisi");
        System.out.println(map.get("1"));
    }
}
