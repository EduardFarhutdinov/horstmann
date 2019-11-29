package tom2.ch02.randomAccess;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;

public class RandomAccessTest {
    public static void main(String[] args) throws IOException {
        var staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
//
        try(var out = new  DataOutputStream(new FileOutputStream("employee.dat"))){

            for (Employee e : staff)
                writeData(out, e);
        }
        try (var in = new RandomAccessFile("employee.dat", "r"))
        {
            // retrieve all records into a new array

            // compute the array size
            int n = (int)(in.length() / Employee.RECORD_SIZE);
            var newStaff = new Employee[n];

            // read employees in reverse order
            for (int i = n - 1; i >= 0; i--)
            {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i] = readData(in);
            }

            // print the newly read employee records
            for (Employee e : newStaff)
                System.out.println(e);
        }
    }



    public static void writeData(DataOutput output,Employee e) throws IOException {

        DataIO.writeFixedString(e.getName(),Employee.NAME_SIZE,output);
        output.writeDouble(e.getSalary());
        LocalDate hireDay = e.getHireDay();
        output.writeInt(hireDay.getYear());
        output.writeInt(hireDay.getMonthValue());
        output.writeInt(hireDay.getDayOfMonth());
    }
    public static Employee readData(DataInput in) throws IOException
    {
        String name = DataIO.readFixedString(Employee.NAME_SIZE, in);
        double salary = in.readDouble();
        int y = in.readInt();
        int m = in.readInt();
        int d = in.readInt();
        return new Employee(name, salary, y, m-1, d);
    }
}
