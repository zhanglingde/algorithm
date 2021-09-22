package io.nio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.Buffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest {

    public static long checksumInputStream(Path filename) throws Exception{
        try (InputStream in = Files.newInputStream(filename)) {
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumBufferedInputStream(Path filename) throws Exception {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(filename))){
            CRC32 crc = new CRC32();
            int c;
            while ((c = in.read()) != -1) {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumRandomAccessFile(Path filename) throws Exception{
        try (RandomAccessFile file = new RandomAccessFile(filename.toFile(), "r")) {
            long length = file.length();
            CRC32 crc = new CRC32();

            for (long p = 0; p < length; p++) {
                file.seek(p);
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static long checksumMappedFile(Path filename) throws Exception{
        try(FileChannel channel = FileChannel.open(filename)){
            CRC32 crc = new CRC32();
            int length = (int)channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            for (int p = 0; p < length; p++) {
                int c = buffer.get();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("计算 CRC 校验和...");
        long start = System.currentTimeMillis();
        Path filename = Paths.get("/Library/Java/JavaVirtualMachines/jdk-11.0.12.jdk/Contents/Home/lib/src.zip");

        long crcValue = checksumInputStream(filename);
        long end  =System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println("InputStream 流： "+(end-start) + " ms");

        start = System.currentTimeMillis();
        crcValue = checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println("带缓冲的流："+(end - start) + " ms");

        start = System.currentTimeMillis();
        crcValue = checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println("随机访问 RandomAccessFile: "+(end - start) + " ms");

        start = System.currentTimeMillis();
        crcValue = checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println("缓冲区："+(end - start) + " ms");
    }
}
