package class05;

import javax.swing.*;
import java.util.HashMap;

/**
 * 前缀树
 *
 * @author zhangling 2021/7/12 15:58
 */
public class Code02_TrieTree {


    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            // 字符串由 26 个小写字母组成
            // nexts[i] == null 表示该方向的路不存在
            nexts = new Node1[26];
        }
    }

    // 实现方式一
    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                // 字符减 a（97） 的 ASCII 表示该字符的位置
                path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        // word 这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < str.length; i++) {
                path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;

        }

        public void delete(String word) {
            while (search(word) > 0) {
                char[] chs = word.toCharArray();
                Node1 node = root;
                node.pass--;
                int path = 0;
                for (int i = 0; i < chs.length; i++) {
                    path = chs[i] - 'a';
                    if (--node.nexts[path].pass == 0) { // 前面字符提前减到0，后面没有必要再判断
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }

        }

        // 所有加入的字符串中，有几个是以 pre 这个字符串作为前缀的
        public int prefixNum(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = root;
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;

        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }

        @Override
        public String toString() {
            return "Node2{" +
                    "pass=" + pass +
                    ", end=" + end +
                    ", nexts=" + nexts +
                    '}';
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i];
                if (!node.nexts.containsKey(path)) {
                    node.nexts.put(path, new Node2());
                }
                node = node.nexts.get(path);
                node.pass++;
            }
            node.end++;
        }

        // 搜索 字符串出现过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.end;
        }

        public void delete(String word) {
            while (search(word) > 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                int path = 0;
                node.pass--;
                for (int i = 0; i < chs.length; i++) {
                    path = (int) chs[i];
                    if (--node.nexts.get(path).pass == 0) {  // 不存在该字符串
                        node.nexts.put(path, null);
                        return;
                    }
                    node = node.nexts.get(path);

                }
                node.end--;
            }
        }

        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            Node2 node = root;
            char[] chs = pre.toCharArray();
            int path = 0;
            for (int i = 0; i < chs.length; i++) {
                path = chs[i];
                if (!node.nexts.containsKey(path)) {
                    return 0;
                }
                node = node.nexts.get(path);
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {
        Trie2 trie2 = new Trie2();
        trie2.insert("abc");
        //trie2.insert("abd");
        trie2.insert("ab");
        //trie2.insert("acd");
        trie2.delete("ab");
        System.out.println("trie2.search(\"ab\") = " + trie2.search("ab"));
        //System.out.println("trie2.prefixNumber(\"ab\") = " + trie2.prefixNumber("ab"));
        //trie2.delete("abe");
        //System.out.println("trie2.search(\"ab\") = " + trie2.search("ab"));


    }

    public static void printTrie2(Node2 root) {

    }
}
