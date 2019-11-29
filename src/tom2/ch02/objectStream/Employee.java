package tom2.ch02.objectStream;

import java.io.Serializable;
import java.time.LocalDate;

public class Employee implements Serializable {

    private String name;
    private double salary;
    private LocalDate hireDay;

    public Employee() {
    }

    public Employee(String name , double salary ,int year,int month,int day) {
        this.name = name;
        this.salary = salary;
        hireDay = LocalDate.of(year,month,day);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }

    public void raiseSalary(double byPercent){
        double size = salary * byPercent;
        salary += size;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
