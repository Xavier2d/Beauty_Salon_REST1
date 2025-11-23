package ws.beauty.salon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ws.beauty.salon.model.Client;
import java.util.Optional;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
 @Query(value = "SELECT * FROM clients WHERE LOWER(first_name) = LOWER(:name) OR LOWER(last_name) = LOWER(:name);", nativeQuery = true)
    List<Client> findByNameIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT * FROM clients WHERE LOWER(email) = LOWER(:email);", nativeQuery = true)
    Optional<Client> findByEmailIgnoreCase(@Param("email") String email);
}
