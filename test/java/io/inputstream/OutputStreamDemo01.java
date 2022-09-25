package io.inputstream;

import java.io.*;

public class OutputStreamDemo01 {
    public static void main(String[] args) throws Exception {
        OutputStream fos = new FileOutputStream("fos.txt");
        // write 方法写一个字节，97 对应 a
        fos.write(97);
        fos.write(57);

        // 写一个字节数组
        byte[] bytes = {97, 98, 99, 100, 101};
        fos.write(bytes);

        // write写一个字节数组的一部分
        fos.write(bytes,1,3);

        InputStream fin = new FileInputStream("fos.txt");
        // 读取一个字节
        int read = fin.read();
        System.out.println("read = " + read);

        byte[] bys = new byte[5];
        int read1 = fin.read(bys);
        System.out.println("read1 = " + read1);

    }
}
