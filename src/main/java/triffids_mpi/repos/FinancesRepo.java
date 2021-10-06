package triffids_mpi.repos;

import triffids_mpi.domain.Finances;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinancesRepo extends CrudRepository<Finances, Integer> {
    List<Finances> findByType(String type);
}