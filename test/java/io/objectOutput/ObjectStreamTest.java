package io.objectOutput;

import java.io.*;

public class ObjectStreamTest {
    public static void main(String[] args) throws Exception{
        Employee zhang = new Employee("zhang", 1000.00);
        Manager xiao = new Manager("xiao", 2000.00);
        xiao.setSecretary(zhang);
        Manager hei = new Manager("hei", 2200.00);
        hei.setSecretary(zhang);

        Employee[] staff = new Employee[3];
        staff[0] = zhang;
        staff[1] = xiao;
        staff[2] = hei;

        // 保存所有员工数据到 employee.dat（序列化）
        try(ObjectOutput out = new ObjectOutputStream(new FileOutputStream("employee.dat"))){
            out.writeObject(staff);
        }

        // 反序列化员工信息
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat"))) {
            Employee[] newStaff = (Employee[]) in.readObject();
            // 修改秘书的薪资
            newStaff[0].setSalary(1500.00);
            for (Employee e : newStaff) {
                System.out.println("e = " + e);
            }
        }
    }
}
