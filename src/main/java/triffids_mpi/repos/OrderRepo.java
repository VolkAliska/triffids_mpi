package triffids_mpi.repos;

import triffids_mpi.domain.OrderCard;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<OrderCard, Integer>  {
//    List <Order> findByDate(String date);
}
