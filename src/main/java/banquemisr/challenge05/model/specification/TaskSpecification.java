package banquemisr.challenge05.model.specification;

import banquemisr.challenge05.model.entity.Task;
import banquemisr.challenge05.model.enums.Priority;
import banquemisr.challenge05.model.enums.Status;
import banquemisr.challenge05.utils.ObjectChecker;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TaskSpecification {

//    public static Specification<Task> id(Long id){
//
//        return (root, query, builder) -> id == null ? builder.conjunction() : builder.equal(root.get("id"), id);
//    }

    public static Specification<Task> status(Status status){

        return (root, query, builder) -> status == null ? builder.conjunction()
                : builder.equal(root.get("status"), status);
    }

    public static Specification<Task> priority(Priority priority){

        return (root, query, builder) -> priority == null ? builder.conjunction()
                : builder.equal(root.get("priority"), priority);
    }

    public static Specification<Task> dueDateBefore(LocalDateTime dueDate){

        return (root, query, builder) -> dueDate == null ? builder.conjunction()
                : builder.lessThan(root.get("dueDate"), dueDate);
    }

    public static Specification<Task> searchWord(String searchField){

        return (root, query, cb) -> {
            if (ObjectChecker.isEmptyOrNull(searchField))
                return cb.conjunction();
            else {
                try{
                    return cb.equal(root.get("id"), Long.parseLong(searchField));
                } catch (Exception ex){
                    return cb.or(
                            cb.like(cb.lower(root.get("title")), "%" + searchField.toLowerCase() + "%"),
                            cb.like(cb.lower(root.get("description")), "%" + searchField.toLowerCase() + "%"));
                }
            }
        };
    }






}
