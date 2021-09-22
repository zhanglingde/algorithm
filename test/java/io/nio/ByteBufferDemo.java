package io.nio;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ByteBufferDemo {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/home");
        FileChannel channel = FileChannel.open(path);
        test01(channel);


    }

    private static void test01(FileChannel channel) throws Exception{
        // 获取缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        ByteBuffer wrapBuffer = ByteBuffer.wrap(new byte[1024]);
        // 将通道的数据填充到缓冲区
        channel.read(buffer);
        channel.position();
        // 切换写模式，将缓冲区的内容写出通道中
        buffer.flip();
        channel.write(buffer);

    }
}
