package tom1.ch_09;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class MapTest  extends LinkedHashMap{

    public static void main(String[] args) throws IOException {
//        var staff =new LinkedHashMap<String,Employee>(128,0.75F,true){
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<String, Employee> eldest) {
//                return size()>4;
//            }
//        };

//        var staff = new HashMap<String,Employee>();
//        staff.put("144-25-5464", new Employee("Amy Lee"));
//        staff.put("567-24-2546", new Employee("Harry Hacker"));
//        staff.put("157-62-7935", new Employee("Gary Cooper"));
//        staff.put("456-62-5527", new Employee("Francesca Cruz"));
//
        Set<String> terminatedIDs = new HashSet<>();
//        terminatedIDs.add("567-24-2546");
//        terminatedIDs.add("456-62-5527");
//
//        System.out.println(staff);
//
//        staff.keySet().removeAll(terminatedIDs);
//
//        System.out.println(staff);//print all entries
//
////        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,4,10,43,5,6,7));
////        System.out.println(numbers);
////        Collections.sort(numbers);
////        System.out.println(numbers);

//        List<Employee> employees = new LinkedList<>();
//        employees.add(new Employee("Lex"));
//        employees.add(new Employee("John"));
//        employees.add(new Employee("Edward"));
//        employees.add(new Employee("Tom"));
//        employees.add(new Employee("Jerry"));
//
//        System.out.println(employees + "Изначальный список");
//
//        employees.sort(Comparator.comparing(Employee::getName));
//        System.out.println(employees + "Отсортированный список по имени");
//        Collections.shuffle(employees);
//        System.out.println(employees + "Случайный порядок 1");
//        Collections.shuffle(employees);
//        System.out.println(employees + "Случайный порядок 2");

        Employee[] employees = new Employee[3];
        employees[0] = new Employee("erwer");
        employees[1] = new Employee("erwer");
        employees[2] = new Employee("erwer");

        for (Employee em:employees
             ) {
            System.out.println(em);

        }
        var staff = new HashSet<Employee>(List.of(employees));

        Employee[] employees1 = staff.toArray(new Employee[staff.size()]);

        var settings = new Properties();
        settings.setProperty("Width","600");
        settings.setProperty("filename","qqqq");
        var out = new FileOutputStream("program.properties");
        settings.store(out,"Program Properties");

    }


    static class Employee {
        private String name;
        private double salary;

        public Employee(String name) {
            this.name = name;
            salary = 0;
        }

        public String getName() {
            return name;
        }
        //
        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
//        @Override
//        public String toString() {
//            return "Employee{"+ name +"}";
//        }


    }
}
