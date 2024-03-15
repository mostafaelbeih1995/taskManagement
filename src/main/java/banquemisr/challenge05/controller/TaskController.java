package banquemisr.challenge05.controller;


import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.payload.CreateTaskRequest;
import banquemisr.challenge05.model.payload.UpdateTaskRequest;
import banquemisr.challenge05.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;



    @PostMapping
    public Task createTask(@RequestBody CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @PutMapping("/{taskId}")
    public Task updateTask(@RequestBody UpdateTaskRequest request, @PathVariable Long taskId){
        return taskService.updateTask(request, taskId);
    }
    @GetMapping
    public List<Task> getTasks(){
        return taskService.getTasks();
    }

    @DeleteMapping("/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}
