package leetcode.code100;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

// 有效的括号
public class Code20_Invalid {
    public static boolean isValid(String str) {
        if (str == null || str.length() < 2) {
            return false;
        }
        while (str.contains("()") || str.contains("[]") || str.contains("{}")) {

            if (str.contains("()")) {
                str = str.replace("()", "");
            }
            if (str.contains("[]")) {
                str = str.replace("[]", "");
            }
            if (str.contains("{}")) {
                str = str.replace("{}", "");
            }
        }

        return str.length() == 0;
    }

    // 使用栈的方式
    public static boolean isValid2(String str) {

        int n = str.length();
        if (n % 2 == 1) {       // 字符串奇数个
            return false;
        }
        Deque<Character> stack = new LinkedList<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                // 遇到右半部分符号与栈顶符号匹配，相同则栈顶符号出栈，不同则规则不正确
                if (stack.isEmpty() || map.get(c) != stack.peek()) {
                    return false;
                }
                stack.pop();
            } else {
                // 左半部分符号入栈
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String str = "[{({{[()]}})}]";

        String s = "[((])]";
        System.out.println("isValid(str) = " + isValid2(s));
    }
}
