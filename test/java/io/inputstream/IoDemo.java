package io.inputstream;

import java.io.*;

public class IoDemo {

    private final static int _1M = 1024 * 1024;

    public static void main(String[] args) throws IOException {
        // 路径分隔符（与系统有关） windows 中是 ; linux 中是 ：
        System.out.println("File.pathSeparator = " + File.pathSeparator);
        // 与系统有关的路径名称分隔符 windows 中是 \ linux 中是 /
        System.out.println("File.separator = " + File.separator);

        File file = new File("/Users/ling/书籍/编码的奥秘.pdf");
        File file2 = new File("/Users/ling/书籍/编码的奥秘22.pdf");
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(file);
            out = new FileOutputStream(file2);
            byte[] bytes = new byte[_1M];
            int len = in.read(bytes);
            while (len != 0) {
                out.write(bytes,0,len);
                len = in.read(bytes);
            }
        } catch (Exception e) {

        }finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }
}
