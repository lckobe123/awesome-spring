package com.ternence.skills.netty.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 获取channel的测试类
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/3/20 19:55
 */
public class GetChannel {
    private final static String PATH = "D:\\user\\idea\\spring-skills\\spring-netty\\src\\main\\resources\\test.txt";
    private static final int SIZE = 1024;

    public static void main(String[] args) {
        //A channel for reading,writing and manipulating a file
        FileChannel fileChannel;
        try {
            //Write the file
            fileChannel = new FileOutputStream(PATH).getChannel();
            fileChannel.write(ByteBuffer.wrap("this is my write content !".getBytes()));
            //关闭channel
            fileChannel.close();

            //Random access
            fileChannel = new RandomAccessFile(PATH, "rw").getChannel();
            fileChannel.position(fileChannel.size());//Move to the end
            fileChannel.write(ByteBuffer.wrap("\n\rthis is another words !".getBytes()));
            fileChannel.close();

            //Read the file
            fileChannel = new FileInputStream(PATH).getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(SIZE);
            fileChannel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
            fileChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
