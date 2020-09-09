package info.ivicel.ch01;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test1 {

    public static void main(String[] args) {
        try {
        RandomAccessFile file = new RandomAccessFile("example.txt", "rw");
            FileChannel fileChannel = file.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(48);
            int count = fileChannel.read(buffer);
            while (count != -1) {
                System.out.println("Read " + count);
                buffer.flip();

                while (buffer.hasRemaining()) {
                    System.out.println((char) buffer.get());
                }

                buffer.clear();
                count = fileChannel.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
