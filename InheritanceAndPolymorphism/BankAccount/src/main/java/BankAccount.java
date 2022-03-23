/*
This is an initial task to understand the inheritance.

Classes CardAccount and DepositAccount must override the class BankAccount.

Method 'take' or 'send' must charge 10% of commission in CardAccount.

In DepositAccount you can withdraw the money at least one month after the last deposit.
 */

public class BankAccount {

    private double amount;

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        if (amountToPut > 0) {
            amount += amountToPut;
        }
    }

    public void take(double amountToTake) {
        if (amountToTake <= amount) {
            amount -= amountToTake;
        }
    }

    public boolean send(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= this.amount) {
            this.take(amount);
            receiver.put(amount);
            return true;
        }
        return false;
    }
}
