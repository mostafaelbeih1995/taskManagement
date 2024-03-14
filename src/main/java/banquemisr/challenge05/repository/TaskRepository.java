package banquemisr.challenge05.repository;

import banquemisr.challenge05.model.entity.Task;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends BaseRepository<Task>, JpaSpecificationExecutor<Task> {


}
