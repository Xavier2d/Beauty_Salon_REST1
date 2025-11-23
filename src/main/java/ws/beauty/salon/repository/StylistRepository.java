package ws.beauty.salon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ws.beauty.salon.model.Stylist;

public interface StylistRepository extends JpaRepository<Stylist, Integer> {

    // Consulta nativa: estilistas por especialidad exacta
    @Query(value = "SELECT * FROM stylists WHERE specialty = :specialty", nativeQuery = true)
    List<Stylist> getStylistsBySpecialty(@Param("specialty") String specialty);

    // Consulta JPQL: coincidencia parcial en especialidad
    @Query("SELECT s FROM Stylist s WHERE LOWER(s.specialty) LIKE LOWER(CONCAT('%', :specialty, '%'))")
    List<Stylist> getStylistsBySpecialtyJPQL(@Param("specialty") String specialty);

    // Consulta estilistas disponibles
    @Query("SELECT s FROM Stylist s WHERE s.available = true")
    List<Stylist> getAvailableStylists();
}
