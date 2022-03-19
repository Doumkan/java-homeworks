/*  An example of the output of the Email list, after entering the LIST command into the console:
       test@test.com
       hello@mail.ru
       - each address on a new line
       - the list must be sorted alphabetically
       - email in different registers is considered the same
          hello@skillbox.ru == HeLLO@SKILLbox.RU
       - print output must be in lowercase
          hello@skillbox.ru
       An example of an error message output for an invalid Email format:
       "Invalid email format"
  */

import java.util.Scanner;

public class Main {
    public static final String WRONG_EMAIL_ANSWER = "Invalid email format";
    public static EmailList emailList = new EmailList();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("0")) {
                break;
            }

            String[] email = input.split("\\s+");
            input = input.substring(input.indexOf(' ') + 1, input.length());

            if (email[0].matches("add")) {
                emailList.add(input);
            }
            if (email[0].matches("list")) {
                emailList.getSortedEmails();
                emailList.getSortedDomains();
            }
        }
    }
}
