package ws.beauty.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.Appointment;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("SELECT a FROM Appointment a WHERE a.status = :status")
    List<Appointment> findByStatus(@Param("status") String status);

    @Query("SELECT a FROM Appointment a WHERE a.client.id = :clientId")
    List<Appointment> findByClient(@Param("clientId") Integer clientId);
}
