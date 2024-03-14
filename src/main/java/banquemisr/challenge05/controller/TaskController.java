package banquemisr.challenge05.controller;


import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task-management")
public class TaskController {

    @Autowired
    private TaskService taskService;



    @GetMapping("/tasks")
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @DeleteMapping("/tasks/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}
