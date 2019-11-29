package tom2.ch02.serialClone;

import java.io.*;
import java.time.LocalDate;

public class SerialClone {
    public static void main(String[] args) throws CloneNotSupportedException {

        var harry = new Employee("Harry Hacker" , 35000 , 1989 , 10 , 1);
        // clone harry
        var harry2 = (Employee) harry.clone();

        // mutate harry
        harry.raiseSalary(10);

        // now harry and the clone are different
        System.out.println(harry);
        System.out.println(harry2);
    }
}
    class SerialCloneable implements Serializable,Cloneable{


        public Object clone() throws CloneNotSupportedException {
            try {
               var bout = new ByteArrayOutputStream();
               try(var out = new ObjectOutputStream(bout)){
                out.writeObject(this);
               }


               try(var bin = new ByteArrayInputStream(bout.toByteArray())){
                   var in = new ObjectInputStream(bin);
                   return in.readObject();
               }
            }catch ( IOException | ClassNotFoundException e ){
                var e2 = new CloneNotSupportedException();
                e2.initCause(e);
                throw  e2;
            }


        }
    }

    class Employee extends SerialCloneable
    {
        private String name;
        private double salary;
        private LocalDate hireDay;

        public Employee(String n, double s, int year, int month, int day)
        {
            name = n;
            salary = s;
            hireDay = LocalDate.of(year, month, day);
        }

        public String getName()
        {
            return name;
        }

        public double getSalary()
        {
            return salary;
        }

        public LocalDate getHireDay()
        {
            return hireDay;
        }

        /**
         Raises the salary of this employee.
         @byPercent the percentage of the raise
         */
        public void raiseSalary(double byPercent)
        {
            double raise = salary * byPercent / 100;
            salary += raise;
        }

        public String toString()
        {
            return getClass().getName()
                    + "[name=" + name
                    + ",salary=" + salary
                    + ",hireDay=" + hireDay
                    + "]";
        }
}

