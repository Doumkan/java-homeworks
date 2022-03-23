public class Operator implements Employee {

    private Company company;
    private double salary;

    @Override
    public double getMonthSalary() {
        salary = 40_000;
        return salary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public Company getCompany() {
        return company;
    }
}
