package ws.beauty.salon.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    @Size(max = 50)
    private String username;

    @NotBlank
    @Size(max = 255)
    private String password;

    @NotBlank
    @Size(max = 20)
    private String role;

    private Integer clientId;
    private Integer stylistId;
}
