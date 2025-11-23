package ws.beauty.salon.service;

import java.util.List;
import ws.beauty.salon.dto.UserRequest;
import ws.beauty.salon.dto.UserResponse;

public interface UserService {
    List<UserResponse> findAll();
    List<UserResponse> getAll(int page, int pageSize);
    UserResponse findById(Integer id);
    UserResponse create(UserRequest request);
    UserResponse update(Integer id, UserRequest request);
    void delete(Integer id);
    List<UserResponse> findByRole(String role);
    List<UserResponse> findByUsername(String username);
    List<UserResponse> findByPassword(String password);

}

