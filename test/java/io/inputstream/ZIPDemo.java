package io.inputstream;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZIPDemo {
    public static void main(String[] args) {
        File file = new File("InputStream.png");
        compression("输入流原型图.zip", file);
//        testZIPOutputStream();
    }

    /**
     * 压缩文件
     *
     * @param zipFilename 压缩后文件名
     * @param file        需要压缩的文件
     */
    public static void compression(String zipFilename, File file) {
        ZipOutputStream zos = null;
        BufferedOutputStream bos = null;
        try {
            zos = new ZipOutputStream(
                    new FileOutputStream(zipFilename));
//            bos = new BufferedOutputStream(zos);
            zip(zos, file);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (zos != null) {
                    zos.close();
                }
            } catch (Exception e) {

            }
        }
    }

    public static void zip(ZipOutputStream zos, File file) throws IOException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files.length == 0) {    // 空目录
                zos.putNextEntry(new ZipEntry(file.getName() + "/"));
            }
            for (File f : files) {
                // 递归处理
                zip(zos, file);
            }
        } else {
            zos.putNextEntry(new ZipEntry(file.getName()));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = -1;
            while ((len = bis.read(bytes)) != -1) {
                zos.write(bytes, 0, len);
            }
            bis.close();
        }
    }

}
