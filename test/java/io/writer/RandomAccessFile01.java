package io.writer;

import java.io.*;
import java.util.Scanner;

public class RandomAccessFile01 {
    public static void main(String[] args) throws Exception {
//        test1();
        Employee emp = new Employee("zhangling", 1000.00);

        // 将员工信息保存到 file.txt 中
        DataOutput out = new DataOutputStream(new FileOutputStream("file.txt"));
        DataIO.writeFixedString(emp.getName(), Employee.NAME_SIZE, out);
        out.writeDouble(emp.getSalary());

        // 读取文件中信息
        DataInput in = new DataInputStream(new FileInputStream("file.txt"));
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        System.out.println("name = " + name);
        Double salary = in.readDouble();
        System.out.println("salary = " + salary);
    }





    static class Employee {

        // 员工姓名使用 40 字符 = 80 字节表示
        public static final int NAME_SIZE = 40;
        String name;
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
