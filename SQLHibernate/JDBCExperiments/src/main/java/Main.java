import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter username");
        String user = scanner.nextLine();

        System.out.println("Enter password");
        String pass = scanner.nextLine();

        String url = "jdbc:mysql://localhost:3306/skillbox";

        String sql = "SELECT pl.course_name, " +
                "(COUNT(pl.subscription_date)/(MAX(MONTH(pl.subscription_date))-MIN(MONTH(pl.subscription_date)) + 1))\n" +
                "    AS average_amount_month\n" +
                "FROM PurchaseList pl\n" +
                "WHERE YEAR(subscription_date)=?\n" +
                "GROUP BY pl.course_name;";

        int year = 2018;

        try (Connection connection = DriverManager.getConnection(url, user, pass);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, year);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String courseName = resultSet.getString("course_name");
                String averageAmountMonth = resultSet.getString("average_amount_month");
                System.out.println(courseName + "\t" + averageAmountMonth);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}