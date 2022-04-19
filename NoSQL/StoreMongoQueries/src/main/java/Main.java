import java.util.*;

public class Main {

    public static void main(String[] args) {
        while (true) {
            System.out.println("Enter your command");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command == null) {
                break;
            }
            ifCommandValid(command);
        }
    }

    private static void ifCommandValid(String command) {
        if (command.matches("CREATE_STORE\\s[A-Za-z]+")) {
            //add new store to the db
            String storeName = command.split("\\s")[1];
            Store store = new Store();
            store.createStore(storeName);
        } else if (command.matches("ADD_PRODUCT\\s[A-Za-z]+\\s\\d+")) {
            //add new product to the db
            String productName = command.split("\\s")[1];
            int price = Integer.parseInt(command.split("\\s")[2]);
            Product product = new Product();
            product.addProductToList(productName, price);
        } else if (command.matches("DISPLAY_PRODUCT\\s[A-Za-z]+\\s[A-Za-z]+")) {
            //add product to the product list of the indicated store
            String product = command.split("\\s")[1];
            String store = command.split("\\s")[2];
            Database.displayUsingPojo(product, store);
        } else if (command.matches("STATS")) {
            Database.getStats();
        } else {
            System.out.println("Wrong command. Try again.");
        }
    }
}
