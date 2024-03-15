package banquemisr.challenge05.model.entity;

import banquemisr.challenge05.model.enums.Priority;
import banquemisr.challenge05.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "task")
@SQLRestriction("deleted <> true")
@SQLDelete(sql = "UPDATE {h-schema} task SET deleted = true WHERE id = ?", check = ResultCheckStyle.COUNT)
public class Task extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Column(name = "priority")
    @Enumerated(value = EnumType.STRING)
    private Priority priority;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

}
