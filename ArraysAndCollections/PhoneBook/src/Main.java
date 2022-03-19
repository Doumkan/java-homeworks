/*
This is a console application imitating a phone book.
It returns the number for an existing name or vice-versa.
Command 'list' returns all contacts from the phone book.
 */

import java.util.Scanner;

public class Main {
    public static PhoneBook myBook = new PhoneBook();

    final static String REGEX_NAME = "[A-Za-z]+";
    final static String REGEX_NUMBER = "\\d{11}";
    final static String WRONG_FORMAT = "Wrong format";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type a name, number or 'list': ");

        while (true) {
            String input = scanner.nextLine();

            String phone = "";
            String name = "";

            if (input.equals("0")) {
                break;
            }
            if (input.matches("list")) {
                System.out.println(myBook.getAllContacts());
                continue;
            }
            if (input.matches(REGEX_NUMBER)) {
                phone = input;
                if (!myBook.ifContainsNumber(phone)) {
                    continue;
                }
            } else if (input.matches(REGEX_NAME)) {
                name = input;
                if (!myBook.ifContainsName(name)) {
                    continue;
                }
            } else {
                System.out.println(WRONG_FORMAT);
                continue;
            }
            myBook.addContact(phone, name);
        }
    }
}
