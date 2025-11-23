package ws.beauty.salon.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ws.beauty.salon.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    // Buscar usuario por nombre de usuario (para login)
    Optional<User> findByUsername(String username);
    // Buscar usuarios por rol (Admin, Stylist, Client, etc.)
    List<User> findByRole(String role);
    // Buscar usuario por contraseña 
    List<User> findByPassword(String password);
}
