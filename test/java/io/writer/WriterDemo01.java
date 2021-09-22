package io.writer;

import java.io.*;
import java.util.Scanner;

public class WriterDemo01 {
    public static void main(String[] args) throws Exception {
//        test1();
//        testWrite();
//        testRead();
        writeData();
        Scanner sc = new Scanner("");
        String line = sc.nextLine();
        String[] emp = line.split("\\|");
        String name = emp[0];
        String salary = emp[1];
        System.out.println("name = " + name);
        System.out.println("salary = " + salary);

    }

    private static void writeData() throws IOException {
        Employee emp = new Employee("zhang", 1000.00);
        PrintWriter out = new PrintWriter(new FileWriter("file.txt"), true);
        out.println(emp.getName() + "|" + emp.getSalary());
    }

    private static void testRead() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("file.txt"), "UTF-8"));
        // readLine 没有输入时返回 null
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println("line = " + line);
        }
    }

    private static void testWrite() throws IOException {
        // PrintWriter out = new PrintWriter("file.txt");
        // 自动冲刷机制默认是禁用的。开启自动冲刷机制，println 方法被调用，缓冲区中的所有字符都被发送到目的地，写入 file.txt
        PrintWriter out = new PrintWriter(new FileWriter("file.txt"), true);
        out.print("hello");
        out.print(" ");
        // println 方法冲刷缓冲区
        out.println("world");
        out.println(1000.00);
    }

    private static void test1() throws IOException {
        // 从控制台读入键盘信息
        InputStreamReader in = new InputStreamReader(System.in);
        int read = in.read();
        System.out.println("read = " + read);

        // Reader 指定字符编码
        InputStreamReader in2 = new InputStreamReader(new FileInputStream("fos.txt"), "UTF-8");
        char[] chs = new char[1024];
        int read1 = in2.read(chs);
        System.out.println("read1 = " + read1);
        for (char ch : chs) {
            System.out.println("ch = " + ch);
        }
//        System.out.println("chs.toString() = " + chs.toString());
    }

    static class Employee {
        String name;
        Double salary;

        public Employee(String name, Double salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Double getSalary() {
            return salary;
        }

        public void setSalary(Double salary) {
            this.salary = salary;
        }
    }
}
