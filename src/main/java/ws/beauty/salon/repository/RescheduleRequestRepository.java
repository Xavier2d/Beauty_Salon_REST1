package ws.beauty.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.RescheduleRequest;

import java.util.List;

public interface RescheduleRequestRepository extends JpaRepository<RescheduleRequest, Integer> {

    @Query("SELECT r FROM RescheduleRequest r WHERE r.client.id = :clientId")
    List<RescheduleRequest> findByClientId(@Param("clientId") Integer clientId);



    @Query("SELECT r FROM RescheduleRequest r WHERE r.status = :status")
    List<RescheduleRequest> findByStatus(@Param("status") String status);
}

