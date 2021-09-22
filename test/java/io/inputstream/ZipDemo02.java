package io.inputstream;

import java.io.File;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipDemo02 {
    public static void main(String[] args) throws Exception {
        File file = new File("/Users/ling/Desktop/test.zip");
        ZipInputStream zin = new ZipInputStream(new FileInputStream(file));
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null) {
            entry = zin.getNextEntry();
        }
        ZipEntry ze1 = zin.getNextEntry();
        ZipEntry ze2 = zin.getNextEntry();
        ZipEntry ze3 = zin.getNextEntry();
        System.in.read();
    }
}
