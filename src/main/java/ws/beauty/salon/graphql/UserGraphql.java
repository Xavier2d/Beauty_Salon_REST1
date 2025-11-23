package ws.beauty.salon.graphql;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ws.beauty.salon.dto.UserRequest;
import ws.beauty.salon.dto.UserResponse;
import ws.beauty.salon.service.UserService;

@Controller
public class UserGraphql {

    @Autowired
    private UserService service;

    // -------------------- Queries --------------------

    @QueryMapping
    public List<UserResponse> findAllUsers() {
        return service.findAll();
    }

    @QueryMapping
    public List<UserResponse> getAllUsers(@Argument int page, @Argument int pageSize) {
        return service.getAll(page, pageSize);
    }

    @QueryMapping
    public UserResponse findUserById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<UserResponse> findUsersByRole(@Argument String role) {
        return service.findByRole(role);
    }

    @QueryMapping
    public List<UserResponse> findUsersByUsername(@Argument String username) {
        return service.findByUsername(username);
    }

    // -------------------- Mutations --------------------

    @MutationMapping
    public UserResponse createUser(@Valid @Argument UserRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public UserResponse updateUser(@Argument Integer id, @Valid @Argument UserRequest req) {
        return service.update(id, req);
    }

    @MutationMapping
    public Boolean deleteUser(@Argument Integer id) {
        service.delete(id);
        return true;
    }
}
