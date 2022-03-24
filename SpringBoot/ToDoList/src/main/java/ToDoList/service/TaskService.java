package ToDoList.service;

import ToDoList.repository.TaskRepository;
import ToDoList.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public boolean ifTaskEmpty(Task task) {
        return task.getText().equals("") || task.getText() == null;
    }

    public void filterForUpdate(List<Task> todoList) {

        List<Task> filteredList = todoList.stream().filter(task -> taskRepository.findById(task.getId()).isPresent())
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            return;
        }

        filteredList.forEach(task -> {
            task.setId(task.getId());
            saveTask(task);
        });
    }

    public void filterForAdding(List<Task> todoList) {

        List<Task> filteredList = todoList.stream()
                .filter(task -> !ifTaskEmpty(task))
                .collect(Collectors.toList());

        if (filteredList.isEmpty()) {
            return;
        }
        filteredList
                .forEach(task -> {
                    if (task.getPriority() == 0) {
                        task.setPriority(1);
                    }
                    saveTask(task);
                });
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public boolean ifTaskNull(int id) {
        return taskRepository.findById(id).isEmpty();
    }

    public void clearTodoList() {
        taskRepository.deleteAll();
    }

    public void deleteOneTask(int id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        Iterable<Task> taskIterable = taskRepository.findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskIterable) {
            tasks.add(task);
        }
        return tasks;
    }

    public TaskRepository getTaskRepository() {
        return taskRepository;
    }
}
