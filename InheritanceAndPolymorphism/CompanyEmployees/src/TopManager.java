public class TopManager implements Employee {
    private Company company;
    private double salary;

    @Override
    public double getMonthSalary() {
        salary = 90_000;
        if (company.getIncome() > 10_000_000) {
            salary = salary * 1.5;
        }
        return (int) salary;
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
