package banquemisr.challenge05.service;

import banquemisr.challenge05.exception.ExceptionList;
import banquemisr.challenge05.exception.RecordNotFoundException;
import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.enums.Status;
import banquemisr.challenge05.model.filter.TaskFilter;
import banquemisr.challenge05.model.payload.CreateTaskRequest;
import banquemisr.challenge05.model.payload.MailRequest;
import banquemisr.challenge05.model.payload.MailResponse;
import banquemisr.challenge05.model.payload.UpdateTaskRequest;
import banquemisr.challenge05.model.specification.TaskSpecification;
import banquemisr.challenge05.repository.TaskRepository;
import banquemisr.challenge05.service.email.EmailService;
import banquemisr.challenge05.utils.ObjectChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmailService emailService;

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
            Task task = taskRepository.findById(taskId).orElseThrow(() -> new RecordNotFoundException(ExceptionList.RECORD_NOT_FOUND));
            prepateTaskData(request, task);
            taskRepository.save(task);
            return task;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void prepateTaskData(UpdateTaskRequest request, Task task){

        if(ObjectChecker.isNotEmptyOrNull(request.getDescription())){
            task.setDescription(request.getDescription());
        }
        if(ObjectChecker.isNotEmptyOrNull(request.getTitle())){
            task.setTitle(request.getTitle());
        }
        if(ObjectChecker.isNotEmptyOrNull(request.getStatus())){
            task.setStatus(request.getStatus());
        }
        if(ObjectChecker.isNotEmptyOrNull(request.getPriority())){
            task.setPriority(request.getPriority());
        }
        if(ObjectChecker.isNotEmptyOrNull(request.getDueDate())){
            task.setDueDate(request.getDueDate());
        }
    }


    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

    public List<Task> getTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    public List<Task> getTasks(TaskFilter filter){
        if(filter.getOffset() == 0 ){
            filter.setOffset(0);
        }
        if(filter.getLimit() == 0){
            filter.setLimit(3);
        }
        Specification<Task> specification = buildSpecification(filter);
        PageRequest pageRequest = buildPagination(filter);
        List<Task> tasks = executeSpecification(specification, filter, pageRequest);
        return tasks;
    }

    private PageRequest buildPagination(TaskFilter filter){

        return PageRequest.of(filter.getOffset(), filter.getLimit());
    }

    private Specification<Task> buildSpecification(TaskFilter filter){

        return Specification.where(
                TaskSpecification.dueDateBefore(filter.getDueDate())
                        .and(TaskSpecification.priority(filter.getPriority()))
                        .and(TaskSpecification.status(filter.getStatus()))
                        .and(TaskSpecification.searchWord(filter.getSearchField()))
        );
    }

    private List<Task> executeSpecification(Specification<Task> specification, TaskFilter filter, PageRequest pageRequest){

        Page<Task> page;
        if (ObjectChecker.isEmptyOrNull(filter))
            page = taskRepository.findAll(pageRequest);
        else
            page = taskRepository.findAll(specification, pageRequest);
        return page.getContent();
    }

//    @Scheduled(cron = "0 0 8 * * *")
//    @Scheduled(fixedRate = 20000)
    private void sendEmailForTasks(){

        LocalDateTime dueDate = LocalDateTime.now().plusDays(7);
        List<Task> tasks = taskRepository.findByDueDateBeforeAndStatusNot(dueDate, Status.DONE);
        MailRequest request = new MailRequest();
        request.setName("Tickets needs to be done");
        request.setSubject("Work on these texts");
        StringBuilder body = new StringBuilder();
        for(Task task : tasks){

            body.append(task.getTitle()).append("\n").append(task.getStatus()).append("\n");
        }
        request.setTemplate(body.toString());
        MailResponse response = emailService.sendEmail(request);
    }
}
