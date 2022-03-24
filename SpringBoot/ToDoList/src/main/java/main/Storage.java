package main;

import ToDoList.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {
    private static AtomicInteger currentId = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, Task> tasks = new ConcurrentHashMap<>();

    public static List<Task> getToDoList() {
        return new ArrayList<>(tasks.values());
    }

    public static int addTask(Task task) {
        int id = currentId.getAndIncrement();
        task.setId(id);
        tasks.put(id, task);
        return id;
    }

    public static Task getTask(int taskId) {
        if (tasks.containsKey(taskId)) {
            return tasks.get(taskId);
        }
        return null;
    }

    public static ConcurrentHashMap<Integer, Task> getTasks() {
        return tasks;
    }
}
