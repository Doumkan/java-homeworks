public class MovementRecord {
    private double income, expense;
    private String expenseName;

    public MovementRecord(String expenseName, double income, double expense) {
        this.expenseName = expenseName;
        this.income = income;
        this.expense = expense;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public double getExpense() {
        return expense;
    }

    public double getIncome() {
        return income;
    }

    public String toString() {
        return "Expense name: " + expenseName + " Income: " + income + " Expense: " + expense;
    }
}
