package ws.beauty.salon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @JsonProperty("id user")
    private Integer id;
    @JsonProperty("user name")
    private String username;
    @JsonProperty("role")
    private String role;
    @JsonProperty("id client")
    private Integer clientId;
    @JsonProperty("client name")
    private String clientName;
    @JsonProperty("id stylist")
    private Integer stylistId;
    @JsonProperty("stylist name")
    private String stylistName;
}
