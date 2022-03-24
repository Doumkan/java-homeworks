package ToDoList.controllers;

import ToDoList.model.Task;
import ToDoList.repository.TaskRepository;
import ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class DefaultController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("/")
    public String index(Model model){

        Iterable<Task> todoList = taskService.getTaskRepository().findAll();
        ArrayList<Task> tasks = new ArrayList<>();
        for(Task task : todoList){
            tasks.add(task);
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());

        return "index";
    }
}
