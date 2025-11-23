package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AttendanceLogRequest {
    @NotBlank(message = "El ID del estilista es requerido")
    @JsonProperty("id stylist")
    private Integer stylistId;

    @JsonProperty("check in")
    private LocalDateTime checkIn;

    @JsonProperty("check out")
    private LocalDateTime checkOut;
    
}
