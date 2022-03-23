import java.time.LocalDate;

public class DepositAccount extends BankAccount {

    private LocalDate lastIncome;

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
        lastIncome = LocalDate.now();
    }

    @Override
    public void take(double amountToTake) {
        if (LocalDate.now().isAfter(lastIncome.plusMonths(1))) {
            super.take(amountToTake);
        }
    }

    @Override
    public boolean send(BankAccount receiver, double amount) {
        if (LocalDate.now().isAfter(lastIncome)) {
            return super.send(receiver, amount);
        }
        return false;
    }
}
