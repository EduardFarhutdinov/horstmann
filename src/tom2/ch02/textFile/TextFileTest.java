package tom2.ch02.textFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class TextFileTest {
    public static void main(String[] args)  {
        var staff = new Employee[3];
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);


//        PrintWriter out = null;
//        try {
//            out = new PrintWriter("/home/home-pc/Рабочий стол/java_core_with_horstmann_tom1/tom1/src/tom2/ch02/textFile/employee.txt", StandardCharsets.UTF_8);
//            writeData(staff,out);
//        } catch ( IOException e ) {
//            e.printStackTrace();
//        }finally {
//            out.close();
//        }

        Scanner in = null;
        try {
             in = new Scanner(new FileInputStream("/home/home-pc/Рабочий стол/" +
                    "java_core_with_horstmann_tom1/tom1/src/tom2/ch02/textFile/employee.txt") , "UTF-8");

            Employee[] employess =readData(in);

            for ( Employee e:employess
                   ) {
                System.out.println(e);

            }
//            Arrays.stream(employess).forEach(System.out::println);
        } catch ( FileNotFoundException e ) {
            e.printStackTrace();
        }finally {
            in.close();
        }


    }


    public static void writeData(Employee[] employees,PrintWriter printWriter){
        printWriter.println(employees.length);
        for (Employee e : employees)
            writeEmployee(printWriter, e);
    }

    public static void writeEmployee(PrintWriter out, Employee e)
    {
        out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
    }


    public static Employee[] readData(Scanner in){
        int n = in.nextInt();
        in.nextLine();

        var employees = new Employee[n];

        for (int i = 0; i < n; i++)
        {
            employees[i] = readEmployee(in);
        }
        return employees;
    }

    private static Employee readEmployee(Scanner in) {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        String name = tokens[0];
        double salary = Double.parseDouble(tokens[1]);
        LocalDate date = LocalDate.parse(tokens[2]);
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        return new Employee(name,salary,year,month,day);
    }
}
