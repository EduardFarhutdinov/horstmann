package tom2.ch02.objectStream;

import java.io.*;
import java.util.Arrays;

public class ObjectStreamTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var harry = new Employee("Harry Hacker",50000,1989,10,1);
        var carl = new Manager("Carl Cracker",80000,1987,12,15);

        carl.setSecretary(harry);

        var tony = new Manager("Tony Tester", 40000, 1990, 3, 15);

        tony.setSecretary(harry);

        var staff = new Employee[3];
        staff[0] = carl;
        staff[1] =harry;
        staff[2] = tony;

        writeToFileEmployee(staff);

        readFromFileEmployee();
    }

    private static void readFromFileEmployee() throws IOException, ClassNotFoundException {
        try(var in = new ObjectInputStream(new FileInputStream("employee.txt"))){
            var newStaff = (Employee[]) in.readObject();

            newStaff[1].raiseSalary(10);

            Arrays.stream(newStaff).forEach(System.out::println);
        }
    }

    private static void writeToFileEmployee(Employee[] staff) throws IOException {
        try(var out = new ObjectOutputStream(new FileOutputStream("employee.txt"))){
            out.writeObject(staff);
        }
    }
}
