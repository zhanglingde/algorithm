package io.writer;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.Scanner;

public class RandomAccessFile02 {
    public static void main(String[] args) throws Exception {
        Employee[] emps = new Employee[3];
        emps[0] = new Employee("zhang", 1000.00);
        emps[1] = new Employee("ling", 2000.00);
        emps[2] = new Employee("bai", 3000.00);

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("file.txt"))) {
            // 将所有员工信息保存到 file.txt 中
            for (Employee emp : emps) {
                writeData(emp, out);
            }
        }


        // 读取文件并解析员工数据到一个新的员工数组中
        try (RandomAccessFile in = new RandomAccessFile("file.txt", "r")) {
            // 计算数组大小，流的总字节数/每个对象的字节数
            int n = (int) (in.length() / Employee.RECODE_SIZE);
            Employee[] newEmps = new Employee[n];

            for (int i = n - 1; i >= 0; i--) {
                newEmps[i] = new Employee();
                // 将文件流指针设置到距文件开头 两个对象长度字节处，即直接开始读第 3 个对象的字节信息解析
                in.seek(i * Employee.RECODE_SIZE);
                newEmps[i] = readData(in);
            }
            for (Employee e : newEmps) {
                System.out.println("e = " + e);
            }
        }

    }


    private static Employee readData(DataInput in) throws IOException {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        return new Employee(name, salary);
    }


    private static void writeData(Employee emp, DataOutput out) throws IOException {
        DataIO.writeFixedString(emp.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(emp.getSalary());
    }


    static class Employee {
        // 员工姓名使用 40 字符 = 80 字节存储
        public static final int NAME_SIZE = 40;
        // 整个记录使用 80 + 8 个字节存储
        public static final int RECODE_SIZE = 88;
        String name;
        // 薪资 double 类型 8 个字节
        Double salary;


        public Employee() {
        }

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

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
