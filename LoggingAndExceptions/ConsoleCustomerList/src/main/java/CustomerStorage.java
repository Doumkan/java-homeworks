import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        final String NUMBER_REGEX = "\\+?7\\d{10}";

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong command! Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }
        if (!components[2].contains("@")) {
            throw new IncorrectEmailException("Wrong email format. Correct format: \n" +
                    "vasily.petrov@gmail.com");
        }
        if (!components[3].matches(NUMBER_REGEX)) {
            throw new IncorrectPhoneNumberException("Wrong number format. Correct format: \n" +
                    "+79215637722");
        }

        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
        System.out.println("Customer " + name + " has been added successfully!");
    }

    public void listCustomers() {
        if (storage.isEmpty()) {
            System.out.println("There are no customers yet");
        } else {
            storage.values().forEach(System.out::println);
        }
    }

    public void removeCustomer(String name) {

        String[] components = name.split("\\s+");
        try {
            if (!storage.isEmpty() || components.length == 2) {
                storage.remove(name);
                System.out.println("Customer " + name + " has been removed!");
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}