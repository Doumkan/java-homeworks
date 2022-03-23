public class CardAccount extends BankAccount {

    @Override
    public void take(double amountToTake) {
        amountToTake += amountToTake / 100;
        super.take(amountToTake);
    }

    @Override
    public boolean send(BankAccount receiver, double amount) {
        amount += amount / 100;
        return super.send(receiver, amount);
    }
}