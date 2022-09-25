package io.objectOutput;

import java.io.Serializable;

public class Employee implements Serializable {

    String name;
    Double salary;

    public Employee(String name,Double salary) {
        this.name = name;
        this.salary = salary;
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
