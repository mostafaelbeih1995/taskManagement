package banquemisr.challenge05.model.filter;

import banquemisr.challenge05.model.enums.Priority;
import banquemisr.challenge05.model.enums.Role;
import banquemisr.challenge05.model.enums.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TaskFilter {

    private int offset;
    private int limit;
    private Long id;
    private Long pageId;
    private String title;
    private String description;
    private Status status;
    private Priority priority;
    private LocalDateTime dueDate;
    private String searchField;


}
