/*
Company hires operators, managers and top-managers.

Operators have a fixed salary.
Managers have fixed salary + 5% of the profit made for the company.
Top-managers have fixed salary, but if the income of the company is bigger than 10 million rub, they receive 1,5 salary.

The managers generate company's income.
 */
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Company company = new Company();
        ArrayList<Employee> list = new ArrayList<>();
        ArrayList<Employee> managers = new ArrayList<>();

        //create 180 operators
        for (int i = 0; i < 180; i++) {
            Employee operator = new Operator();
            list.add(operator);
        }
        //create 80 managers
        for (int i = 0; i < 80; i++) {
            Manager manager = new Manager();
            list.add(manager);
            managers.add(manager);
        }
        //create 10 top managers
        for (int i = 0; i < 10; i++) {
            Employee topManager = new TopManager();
            list.add(topManager);
        }
        //hire all of them
        company.hireAll(list);

        //income of the company
        System.out.println("Income of the company: " + company.getIncome(managers) + " rub.");

        System.out.println("Amount of employees in the company: " + company.getNumberOfEmployees(list));

        //fire 50% of the staff
        company.fire(list, company.getNumberOfEmployees(list) / 2);

        //company.list(list);
        System.out.println("Amount of employees in the company: " + company.getNumberOfEmployees(list));

    }
}
