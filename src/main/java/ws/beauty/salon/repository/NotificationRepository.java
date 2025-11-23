package ws.beauty.salon.repository;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ws.beauty.salon.model.Notification;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    // 🔹 Buscar notificaciones por cliente
    @Query(value = "SELECT * FROM notifications WHERE id_client = :clientId", nativeQuery = true)
    List<Notification> findByClientId(@Param("clientId") Integer clientId);

    // 🔹 Buscar notificaciones enviadas por tipo (SMS, email, WhatsApp)
    @Query(value = "SELECT * FROM notifications WHERE LOWER(sent_via) = LOWER(:sentVia)", nativeQuery = true)
    List<Notification> findBySentVia(@Param("sentVia") String sentVia);

    // 🔹 Buscar notificaciones en un rango de fechas
    @Query(value = "SELECT * FROM notifications WHERE sent_at BETWEEN :start AND :end", nativeQuery = true)
    List<Notification> findBySentAtBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    // 🔹 Contar notificaciones por cliente
    @Query(value = "SELECT COUNT(*) FROM notifications WHERE id_client = :clientId", nativeQuery = true)
    long countByClientId(@Param("clientId") Integer clientId);
   
}
