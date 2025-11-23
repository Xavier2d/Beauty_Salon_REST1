package ws.beauty.salon.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ws.beauty.salon.model.ServiceCategory;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
    List<ServiceCategory> findByCategoryNameContainingIgnoreCase(String categoryName);
}
