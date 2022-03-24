import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Movements {
    private static ArrayList<MovementRecord> records;

    public Movements(String pathMovementsCsv) {
        records = new ArrayList<>();
        readDataFromFile(pathMovementsCsv);
    }

    //reads data from file. Creates objects and adds them to record
    private void readDataFromFile(String filepath) {
        try {
            Scanner scanner = new Scanner(new FileReader(filepath));
            String line;
            MovementRecord movementRecord;

            scanner.nextLine(); //skip the first line

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] results = parseProblemNumbers(line).split(",");
                if (results.length != 8) {
                    System.out.println("Wrong line: " + line);
                    continue;
                }
                String expenseName = parseExpenseName(results[5]);
                double income = Double.parseDouble(results[6]);
                double expense = Double.parseDouble(results[7]);

                movementRecord = new MovementRecord(expenseName,
                        income,
                        expense);
                records.add(movementRecord);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //extract the name of the operations
    private String parseExpenseName(String expenseName) {

        Pattern pattern = Pattern.compile("[A-Z]{2,}.+?\\s{3}");
        Matcher matcher = pattern.matcher(expenseName);

        while (matcher.find()) {
            expenseName = expenseName.substring(matcher.start(), matcher.end())
                    .replaceAll("[^A-Za-z\\d\\s]", "")
                    .trim();
        }
        return expenseName;
    }

    //replace problem numbers "303,67" with 303.67
    private String parseProblemNumbers(String line) {

        Pattern pattern = Pattern.compile("\"\\d+,\\d+\"");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String doubleDigits = line.substring(matcher.start(), matcher.end());
            String replacement = doubleDigits.replaceAll(",", ".");
            replacement = replacement.replaceAll("\"", "");
            line = line.replaceAll(doubleDigits, replacement);
        }
        return line;
    }

    public double getExpenseSum() {
        double sum = 0;
        for (MovementRecord m : records) {
            sum += m.getExpense();
        }
        System.out.println("Expense sum: " + sum + " rub.");
        return sum;
    }

    public double getIncomeSum() {
        double sum = 0;
        for (MovementRecord m : records) {
            sum += m.getIncome();
        }
        System.out.println("Income sum: " + sum + " rub.");
        return sum;
    }

    public void getExpenseStatement() {
        System.out.println("Expense sum by organizations:\n");

        Map<String, Double> expenseStatement = records.stream().filter(i -> i.getExpense() > 0)
                .collect(Collectors.groupingBy(MovementRecord::getExpenseName,
                        Collectors.summingDouble(MovementRecord::getExpense)));

        expenseStatement.forEach((key, value) -> System.out.println(key + "\t" + value));
    }
}