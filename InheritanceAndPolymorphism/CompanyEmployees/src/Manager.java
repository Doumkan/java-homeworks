public class Manager implements Employee {

    private Company company;
    private double salary;
    private static double profitForCompany;
    final private double MANAGER_PERCENT = 0.05;
    final private static double MAX_PROFIT = 140_000;
    final private static double MIN_PROFIT = 115_000;
    final private double FIXED_SALARY = 70_000;

    @Override
    public double getMonthSalary() {
        return (int) salary;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
        profitForCompany = MIN_PROFIT + Math.random() * (MAX_PROFIT - MIN_PROFIT);
        salary = FIXED_SALARY + profitForCompany * MANAGER_PERCENT;
    }

    public static double getProfitForCompany() {
        return profitForCompany;
    }

    @Override
    public Company getCompany() {
        return company;
    }
}
