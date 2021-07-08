package class02;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 哈希表 和 有序表
 * @author zhangling 2021/7/8 11:43
 */
public class HashMapAndSortedMap {
    public static class Node{
        private int value;
        public Node(int value){
            this.value = value;
        }
    }
    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(1, 1);
        System.out.println("map.size() = " + map.size());   // 1

        HashMap<Node,Integer> map2 = new HashMap<>();
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        map2.put(node1, 1);
        map2.put(node2, 1);
        System.out.println("map2.size() = " + map2.size());   // 2
        System.out.println("=============================");

        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(3, "value:3");
        treeMap.put(4, "value:4");
        treeMap.put(8, "value:8");
        treeMap.put(5, "value:5");
        treeMap.put(7, "value:7");
        treeMap.put(2, "value:2");
        treeMap.put(1, "value:1");

        System.out.println("treeMap.containsKey(1) = " + treeMap.containsKey(1));
        System.out.println("treeMap.containsValue(\"value:2\") = " + treeMap.containsValue("value:2"));
        System.out.println("treeMap.firstKey() = " + treeMap.firstKey());           // 1
        System.out.println("treeMap.lastKey() = " + treeMap.lastKey());             // 8
        System.out.println("treeMap.floorKey(3) = " + treeMap.floorKey(6));         // 5
        System.out.println("treeMap.floorKey(3) = " + treeMap.ceilingKey(6));       // 7


    }
}
