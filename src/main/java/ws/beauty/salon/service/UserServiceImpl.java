package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ws.beauty.salon.dto.UserRequest;
import ws.beauty.salon.dto.UserResponse;
import ws.beauty.salon.mapper.UserMapper;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.model.User;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.repository.StylistRepository;
import ws.beauty.salon.repository.UserRepository;

import java.util.List;
@SuppressWarnings("null")
@Service
@Transactional
@RequiredArgsConstructor  // ✨ Lombok genera constructor automáticamente
public class UserServiceImpl implements UserService {

    // ✨ Ahora son 'final' y se inyectan vía constructor
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final StylistRepository stylistRepository;

    @Override
    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<User> users = repository.findAll(pageReq);
        return users.getContent().stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public UserResponse findById(Integer id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));
        return UserMapper.toResponse(user);
    }

    @Override
    public UserResponse create(UserRequest dto) {
        User user = UserMapper.toEntity(dto);

        // ✨ Encriptar contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        // Prevent registering both client and stylist at the same time
        if (dto.getClientId() != null && dto.getStylistId() != null) {
            throw new IllegalArgumentException("A user cannot be linked to both a client and a stylist.");
        }

        // Relation with Client
        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found: " + dto.getClientId()));
            user.setClient(client);
        }

        // Relation with Stylist
        if (dto.getStylistId() != null) {
            Stylist stylist = stylistRepository.findById(dto.getStylistId())
                    .orElseThrow(() -> new EntityNotFoundException("Stylist not found: " + dto.getStylistId()));
            user.setStylist(stylist);
        }

        User saved = repository.save(user);
        return UserMapper.toResponse(saved);
    }

    @Override
    public UserResponse update(Integer id, UserRequest dto) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found: " + id));

        // Prevent updating with both client and stylist
        if (dto.getClientId() != null && dto.getStylistId() != null) {
            throw new IllegalArgumentException("A user cannot be linked to both a client and a stylist.");
        }

        // Update basic fields (sin password)
        existing.setUsername(dto.getUsername());
        existing.setRole(dto.getRole());
        
        // ✨ Solo actualizar password si se proporcionó uno nuevo
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        // Update relations
        if (dto.getClientId() != null) {
            Client client = clientRepository.findById(dto.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found: " + dto.getClientId()));
            existing.setClient(client);
            existing.setStylist(null); // remove stylist link
        } else if (dto.getStylistId() != null) {
            Stylist stylist = stylistRepository.findById(dto.getStylistId())
                    .orElseThrow(() -> new EntityNotFoundException("Stylist not found: " + dto.getStylistId()));
            existing.setStylist(stylist);
            existing.setClient(null); // remove client link
        }

        User saved = repository.save(existing);
        return UserMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("User not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<UserResponse> findByRole(String role) {
        return repository.findByRole(role).stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(List::of)
                .orElse(List.of())
                .stream()
                .map(UserMapper::toResponse)
                .toList();
    }

    @Override
    public List<UserResponse> findByPassword(String password) {
        // ⚠️ ADVERTENCIA: Este método NO debería existir por seguridad
        // Las contraseñas están hasheadas con BCrypt, no se pueden buscar directamente
        // Considera eliminar este método del repository y del service
        return repository.findByPassword(password).stream()
                .map(UserMapper::toResponse)
                .toList();
    }
}
