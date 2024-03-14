package banquemisr.challenge05.service;

import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }
}
