import java.util.Comparator;

public class EmployeeTopComparator implements Comparator<Employee> {

    @Override
    public int compare(Employee o1, Employee o2) {

        return Double.compare(o2.getMonthSalary(), o1.getMonthSalary());
    }
}
