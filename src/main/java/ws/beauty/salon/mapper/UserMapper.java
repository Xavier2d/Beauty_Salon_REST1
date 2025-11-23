package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.UserRequest;
import ws.beauty.salon.dto.UserResponse;
import ws.beauty.salon.model.User;

public final class UserMapper {
    public static UserResponse toResponse(User user) {
        if (user == null) return null;

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .role(user.getRole())
                .clientId(user.getClient() != null ? user.getClient().getId() : null)
                .clientName(user.getClient() != null ? user.getClient().getFirstName() : null)
                .stylistId(user.getStylist() != null ? user.getStylist().getId() : null)
                .stylistName(user.getStylist() != null ? user.getStylist().getFirstName(): null)
                .build();
    }

    public static User toEntity(UserRequest dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        return user;
    }

    public static void copyToEntity(UserRequest dto, User user) {
        if (dto == null || user == null) return;
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
    }
}
