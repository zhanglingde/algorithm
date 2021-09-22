package io.writer;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriterDemo02 {
    public static void main(String[] args) throws Exception {
        Employee[] emps = new Employee[3];
        emps[0] = new Employee("zhang", 1000.00);
        emps[1] = new Employee("ling", 2000.00);
        emps[2] = new Employee("bai", 3000.00);
        // 写数据到 file.txt
//        PrintWriter out = new PrintWriter("file.txt", "UTF-8");
        PrintWriter out = new PrintWriter(new FileWriter("file.txt"),true);
        writeData(emps, out);

        // 读取文件并解析
        Scanner in = new Scanner(new FileInputStream("file.txt"), "UTF-8");
        Employee[] newEmps = readData(in);
        for (Employee e : newEmps) {
            System.out.println("e = " + e);
        }
    }

    private static Employee[] readData(Scanner in) {
        int n = in.nextInt();
        in.nextLine();

        Employee[] emps = new Employee[n];
        for (int i = 0; i < n; i++) {
            emps[i] = readEmployee(in);
        }
        return emps;

    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] split = line.split("\\|");
        String name = split[0];
        Double salary = Double.parseDouble(split[1]);
        return new Employee(name, salary);
    }

    private static void writeData(Employee[] emps, PrintWriter out) {
        out.println(emps.length);
        for (Employee emp : emps) {
            writeEmployee(out, emp);
        }
    }

    private static void writeEmployee(PrintWriter out, Employee emp) {
        out.println(emp.getName() + "|" + emp.getSalary());
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

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
