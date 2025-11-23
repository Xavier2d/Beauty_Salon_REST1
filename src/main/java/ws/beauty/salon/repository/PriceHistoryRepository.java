package ws.beauty.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.PriceHistory;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Integer> {

    @Query("SELECT p FROM PriceHistory p WHERE p.service.id = :serviceId ORDER BY p.changedAt DESC")
    List<PriceHistory> findByServiceId(@Param("serviceId") Integer serviceId);

    @Query("SELECT p FROM PriceHistory p WHERE p.user.id = :userId ORDER BY p.changedAt DESC")
    List<PriceHistory> findByChangedByUser(@Param("userId") Integer userId);
}
