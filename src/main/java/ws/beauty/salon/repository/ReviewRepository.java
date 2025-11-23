package ws.beauty.salon.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    // Consultas especializadas

    // Buscar reviews por cliente
    @Query("SELECT r FROM Review r WHERE r.client.id = :clientId")
    List<Review> findByClientId(@Param("clientId") Integer clientId);

    // Buscar reviews por servicio
    @Query("SELECT r FROM Review r WHERE r.service.id = :serviceId")
    List<Review> findByServiceId(@Param("serviceId") Integer serviceId);

    // Buscar reviews por sentimiento
    @Query("SELECT r FROM Review r WHERE LOWER(r.sentiment) = LOWER(:sentiment)")
    List<Review> findBySentiment(@Param("sentiment") String sentiment);

    // Buscar reviews con calificación mayor o igual a cierta puntuación
    @Query("SELECT r FROM Review r WHERE r.rating >= :rating")
    List<Review> findByRatingGreaterOrEqual(@Param("rating") Integer rating);
}
