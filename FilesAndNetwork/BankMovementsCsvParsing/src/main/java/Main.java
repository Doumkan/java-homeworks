public class Main {
    public static void main(String[] args) {

        Movements movements = new Movements("src/test/resources/movementList.csv");
        movements.getExpenseSum();
        movements.getIncomeSum();
        movements.getExpenseStatement();
    }
}
