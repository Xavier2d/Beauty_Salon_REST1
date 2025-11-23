package ws.beauty.salon.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ws.beauty.salon.model.AttendanceLog;

public interface  AttendanceLogRepository extends JpaRepository<AttendanceLog, Integer>{

    // 🔹 Buscar registros por estilista
    @Query(value = "SELECT * FROM attendance_logs WHERE id_stylist = :stylistId", nativeQuery = true)
    List<AttendanceLog> findByStylistId(@Param("stylistId") Integer stylistId);

    // 🔹 Buscar registros entre fechas (check_in)
    @Query(value = "SELECT * FROM attendance_logs WHERE check_in BETWEEN :start AND :end", nativeQuery = true)
    List<AttendanceLog> findByCheckInBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    // 🔹 Contar registros por estilista
    @Query(value = "SELECT COUNT(*) FROM attendance_logs WHERE id_stylist = :stylistId", nativeQuery = true)
    long countByStylistId(@Param("stylistId") Integer stylistId);

    // 🔹 Contar registros entre fechas
    @Query(value = "SELECT COUNT(*) FROM attendance_logs WHERE check_in BETWEEN :start AND :end", nativeQuery = true)
    long countByCheckInBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    // 🔹 Buscar registro abierto (sin check_out)
    @Query(value = "SELECT * FROM attendance_logs WHERE id_stylist = :stylistId AND check_out IS NULL LIMIT 1", nativeQuery = true)
    Optional<AttendanceLog> findByStylistIdAndCheckOutIsNull(@Param("stylistId") Integer stylistId);
}
