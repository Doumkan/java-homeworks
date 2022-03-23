import java.util.List;

import static java.util.Comparator.*;

public class Main {

    public static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        System.out.println(staff);
    }

    public static void sortBySalaryAndAlphabet(List<Employee> staff) {
        staff.sort(comparing((Employee employee) -> employee.getSalary())
                .thenComparing(employee1 -> employee1.getName()));
    }
}
