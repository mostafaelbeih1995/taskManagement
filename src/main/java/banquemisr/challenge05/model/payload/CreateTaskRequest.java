package banquemisr.challenge05.model.payload;


import banquemisr.challenge05.model.enums.Priority;
import banquemisr.challenge05.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskRequest {

    private String title;
    private String description;
    private Priority priority;
    private Status status;
    private LocalDateTime dueDate;
}
