package banquemisr.challenge05.service;

import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.payload.CreateTaskRequest;
import banquemisr.challenge05.model.payload.UpdateTaskRequest;
import banquemisr.challenge05.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(CreateTaskRequest request){
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .priority(request.getPriority())
                .dueDate(request.getDueDate())
                .build();
        taskRepository.save(task);
        return task;
    }

    public Task updateTask(UpdateTaskRequest request, Long taskId){
        try {
            Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("Record not found"));
            task.setTitle(request.getTitle());
            task.setDescription(request.getDescription());
            task.setStatus(request.getStatus());
            task.setPriority(request.getPriority());
            task.setDueDate(request.getDueDate());
            taskRepository.save(task);
            return task;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }
}
