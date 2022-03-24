package service;

import ToDoList.model.Task;
import ToDoList.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    public static boolean ifTaskEmpty(Task task) {
        return task.getText().equals("") || task.getText() == null;
    }

    public static List<Task> filterForUpdate(List<Task> todoList, TaskRepository taskRepository) {

        return todoList.stream().filter(task -> taskRepository.findById(task.getId()).isPresent())
                .collect(Collectors.toList());
    }

    public static List<Task> filterForAdding(List<Task> todoList) {

        return todoList.stream()
                .filter(task -> !ifTaskEmpty(task))
                .collect(Collectors.toList());
    }
}
