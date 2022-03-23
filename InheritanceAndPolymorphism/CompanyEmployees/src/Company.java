import java.util.ArrayList;

public class Company {

    private int income;
    private ArrayList<Employee> list;

    //constructor generates an income of the company and a list of the employees
    public Company() {
        this.list = new ArrayList<>();
    }

    //returns the income of the company
    public int getIncome() {
        return income;
    }

    public int getIncome(ArrayList<Employee> managers) {
        for (int i = 0; i < managers.size(); i++) {
            income += Manager.getProfitForCompany();
        }
        return income;
    }

    //returns the number of the employees in the company
    public int getNumberOfEmployees(ArrayList<Employee> list) {
        return list.size();
    }

    public ArrayList<Employee> getTopSalaryStaff(ArrayList<Employee> list, int count) {
        list.sort(new EmployeeTopComparator());
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary());
        }
        return list;
    }

    public ArrayList<Employee> getLowestSalaryStaff(ArrayList<Employee> list, int count) {
        list.sort(new EmployeeLowComparator());
        for (int i = 0; i < count; i++) {
            System.out.println(list.get(i).getMonthSalary());
        }
        return list;
    }

    public void hire(Employee employee) {
        list.add(employee);
        employee.setCompany(this);
    }

    public void hireAll(ArrayList<Employee> list) {
        for (Employee employee : list) {
            hire(employee);
        }
    }

    public void fire(ArrayList<Employee> list, int number) {
        for (int i = 0; i < number; i++) {
            list.get(i).setCompany(null);
            list.remove(list.get(i));
        }
    }

    //gets the list of the employees
    public void list(ArrayList<Employee> list) {
        for (Employee employee : list) {
            System.out.println(employee);
        }
    }
}
