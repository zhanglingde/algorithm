package io.inputstream;

import javax.xml.crypto.Data;
import java.io.*;

public class DataInputStreamDemo {
    public static void main(String[] args) throws Exception {
        testDataOutputStream();
        testDataInputStream();
    }

    public static void testDataOutputStream() {
        try {
            File file = new File("data.txt");
            DataOutputStream out = new DataOutputStream(
                    new FileOutputStream(file));

            out.writeBoolean(true);
            out.writeByte(97);
            out.writeChar('x');
            out.writeShort((short) 10);
            out.writeInt(20);
            out.writeLong(30);
            out.writeUTF("hello world");

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    public static void testDataInputStream() {

        try {
            File file = new File("data.txt");
            DataInputStream dis = new DataInputStream(
                    new FileInputStream(file));

            System.out.println("dis.readBoolean() = " + dis.readBoolean());
            System.out.println("dis.readByte() = " + dis.readByte());
            System.out.println("dis.readChar() = " + dis.readChar());
            System.out.println("dis.readShort() = " + dis.readShort());
            System.out.println("dis.readInt() = " + dis.readInt());
            System.out.println("dis.readLong() = " + dis.readLong());
            System.out.println("dis.readUTF() = " + dis.readUTF());

            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
