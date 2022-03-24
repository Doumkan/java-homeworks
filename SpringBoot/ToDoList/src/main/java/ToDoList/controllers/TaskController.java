package ToDoList.controllers;

import ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ToDoList.model.Task;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks/")
    public List<Task> list() {
        return taskService.getAllTasks();
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity getTask(@PathVariable int id) {
        if (taskService.ifTaskNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такого дела не существует!");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks/multiple/")
    public ResponseEntity addTask(@RequestBody List<Task> todoList) {

        taskService.filterForAdding(todoList);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/tasks/")
    public ResponseEntity addOneTask(@RequestBody Task task) {
        if (taskService.ifTaskEmpty(task)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Дело не может быть пустым!");
        }
        if (task.getPriority() == 0) {
            task.setPriority(1);
        }
        taskService.saveTask(task);
        return new ResponseEntity(task, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable int id) {
        if (taskService.ifTaskNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такого дела не существует!");
        }
        taskService.deleteOneTask(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/")
    public ResponseEntity clearTodoList() {
        taskService.clearTodoList();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity updateTask(@PathVariable int id, @RequestBody Task task) {
        if (taskService.ifTaskNull(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        task.setId(id);
        taskService.saveTask(task);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PutMapping("/tasks/multiple/")
    public ResponseEntity updateMultipleTasks(@RequestBody List<Task> todoList) {

        taskService.filterForUpdate(todoList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
