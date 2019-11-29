package tom2.ch02.objectStream;

import java.time.LocalDate;

public class Manager extends Employee {
    private Employee secretary;

    public Manager(String name , double salary , int year , int month , int day) {
        super(name , salary , year , month , day);
        secretary = null;

    }

    public Manager(String name , double salary , LocalDate hireDay ) {

        secretary = null;
    }

    public Employee getSecretary() {
        return secretary;
    }

    public void setSecretary(Employee secretary) {
        this.secretary = secretary;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "secretary=" + secretary +
                "} " + super.toString();
    }
}
