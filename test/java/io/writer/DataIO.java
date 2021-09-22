package io.writer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class DataIO {

    // 写出字符串从开头开始的指定数量的码元（不够，用 0 补齐字符串）
    public static void writeFixedString(String s, int size, DataOutput out) throws IOException {
        for (int i = 0; i < size; i++) {
            char ch = 0;
            if (i < s.length()) {
                ch = s.charAt(i);
            }
            out.writeChar(ch);
        }
    }

    // 从输入流中读入 size 个码元字符串，遇到具有 0 值的字符串，跳过输入字段中剩余的 0 值
    public static String readFixedString(int size, DataInput in) throws IOException {
        StringBuilder builder = new StringBuilder(size);
        int i = 0;
        boolean more = true;
        while (more && i < size) {
            char ch = in.readChar();
            i++;
            if (ch == 0) {
                more = false;
            } else {
                builder.append(ch);
            }
        }
        in.skipBytes(2 * (size - i));
        return builder.toString();
    }
}
