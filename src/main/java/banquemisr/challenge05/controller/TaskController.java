package banquemisr.challenge05.controller;


import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.filter.TaskFilter;
import banquemisr.challenge05.model.payload.CreateTaskRequest;
import banquemisr.challenge05.model.payload.UpdateTaskRequest;
import banquemisr.challenge05.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;



    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Task createTask(@RequestBody @Valid CreateTaskRequest request){
        return taskService.createTask(request);
    }

    @PutMapping("/{taskId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Task updateTask(@RequestBody @Valid UpdateTaskRequest request, @PathVariable Long taskId){
        return taskService.updateTask(request, taskId);
    }

    @PostMapping("/search")
    public List<Task> searchTasks(@RequestBody @Valid TaskFilter filter){

        return taskService.searchTasks(filter);
    }

    @GetMapping
    public List<Task> getTasks(@RequestParam(value = "offset", defaultValue = "0") int offset, @RequestParam(value = "limit", defaultValue = "5") int limit){

        return taskService.getTasks(offset, limit);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "Task deleted successfully";
    }
}
