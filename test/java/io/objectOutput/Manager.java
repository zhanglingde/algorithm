package io.objectOutput;

public class Manager extends Employee{

    private Employee secretary;

    public Manager(String name,Double salary) {
        super(name,salary);
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }
}
