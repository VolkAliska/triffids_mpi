package triffids_mpi.repos;

import triffids_mpi.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepo extends CrudRepository<Task, Integer> {
    List<Task> findByWorkman(String workman);
}
