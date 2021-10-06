package triffids_mpi.repos;

import org.springframework.data.repository.CrudRepository;
import triffids_mpi.domain.Report;

import java.util.List;

public interface ReportRepo extends CrudRepository<Report, Integer> {
    List<Report> findByTitle(String title);
}