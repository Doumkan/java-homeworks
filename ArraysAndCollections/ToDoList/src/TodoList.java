import java.util.ArrayList;

public class TodoList {
    private ArrayList<String> todoList = new ArrayList<>();

    //add task to the end of the list
    public void add(String todo) {
        todoList.add(todo);
        System.out.println("Task " + todo + " added");
    }

    //add task to the given position
    public void add(int index, String todo) {
        if (index <= todoList.size()) {
            todoList.add(index, todo);
        } else {
            todoList.add(todo);
        }
        System.out.println("Task " + todo + " added");
    }

    //replace a task
    public void edit(String todo, int index) {
        if (index <= todoList.size()) {
            String previous = todoList.get(index);
            todoList.set(index, todo);
            System.out.println("Task " + previous + " replaced with " + todo);
        }
    }

    //delete task by given index
    public void delete(int index) {
        if (index < todoList.size()) {
            System.out.println("Task " + todoList.get(index) + " deleted");
            todoList.remove(index);
        }
    }

    //return all tasks
    public ArrayList<String> getTodos() {
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println(i + "-" + todoList.get(i));
        }
        return todoList;
    }
}