package banquemisr.challenge05.repository;

import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.enums.Status;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends BaseRepository<Task>, JpaSpecificationExecutor<Task> {

    List<Task> findByDueDateBeforeAndStatusNot(LocalDateTime time, Status status);
}
