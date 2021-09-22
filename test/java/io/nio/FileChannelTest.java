package io.nio;

import com.sun.nio.file.ExtendedOpenOption;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {
        // 获取某一个磁盘文件的内存映射
        Path path = Paths.get("/Users/ling/codes/algorithm/file.txt");
        FileChannel fileChannel = FileChannel.open(path, ExtendedOpenOption.NOSHARE_READ);
        // channel 获得缓冲区 ByteBuffer
        ByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);

        // 查询缓冲区当前的字节顺序
        ByteOrder order = buffer.order();
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        ByteOrder order1 = buffer.order();

        System.out.println();

    }
}
