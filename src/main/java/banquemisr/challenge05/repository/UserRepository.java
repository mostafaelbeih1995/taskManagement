package banquemisr.challenge05.repository;

import banquemisr.challenge05.model.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends  BaseRepository<User>, JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
}
