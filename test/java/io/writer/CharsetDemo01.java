package io.writer;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.SortedMap;

public class CharsetDemo01 {
    public static void main(String[] args) {
//        Charset charset = Charset.defaultCharset();
//        Charset charset = Charset.forName("ISO-8859-1");
        Charset charset = Charset.forName("UTF-8");
        Set<String> aliases = charset.aliases();
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        // 字符串编码为字节序列
        ByteBuffer buffer = charset.encode("hello world");
        System.out.println("buffer = " + buffer);

        // 解码字节序列
        CharBuffer chars = charset.decode(buffer);
        char c = chars.charAt(0);
        System.out.println("c = " + c);
        System.out.println("chars.toString() = " + chars.toString());
    }
}
