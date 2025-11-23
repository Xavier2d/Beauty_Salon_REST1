package ws.beauty.salon.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ws.beauty.salon.model.Service;


public interface ServiceRepository extends JpaRepository<Service, Integer> {
    List<Service> findByServiceNameContainingIgnoreCase(String serviceName);

    @Query(value = "SELECT * FROM services WHERE id_category = :categoryId", nativeQuery = true)
    List<Service> findByCategoryId(Integer categoryId);
}