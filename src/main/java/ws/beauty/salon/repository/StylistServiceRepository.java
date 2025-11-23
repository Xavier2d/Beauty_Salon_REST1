package ws.beauty.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.StylistService;

import java.util.List;

public interface StylistServiceRepository extends JpaRepository<StylistService, Integer> {

    @Query("SELECT ss FROM StylistService ss WHERE ss.stylist.id = :stylistId")
    List<StylistService> findByStylistId(@Param("stylistId") Integer stylistId);

    @Query("SELECT ss FROM StylistService ss WHERE ss.service.id = :serviceId")
    List<StylistService> findByServiceId(@Param("serviceId") Integer serviceId);
}

