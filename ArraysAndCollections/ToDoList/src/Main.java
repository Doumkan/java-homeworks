/*
Working with arrays and commands
*/

import java.util.Scanner;

public class Main {
    private static TodoList todoList = new TodoList();

    public static void main(String[] args) {
        while (true) {

            System.out.println("Write a task to add or print 'list' to see whole TodoList");

            Scanner scanner = new Scanner(System.in);
            String task = scanner.nextLine();

            if (task == null) {
                break;
            }

            String[] command = task.split("\\s+");
            int index = 0;

            if (command.length == 1 && command[0].matches("list")) {
                todoList.getTodos();
                continue;
            }

            if (command[1].matches("\\d")) {
                index = Integer.parseInt(command[1]);
                task = task.replaceAll(".+\\d\\s", "").trim();
            } else {
                task = task.substring(task.indexOf(' ') + 1, task.length());
            }

            if (command[0].matches("add")) {
                todoList.add(index, task);
            }
            if (command[0].matches("edit")) {
                todoList.edit(task, index);
            }
            if (command[0].matches("delete")) {
                todoList.delete(index);
            }
        }
    }
}
