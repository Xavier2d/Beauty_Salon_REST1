package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class AppointmentRequest {
    private Integer id;
    private LocalDateTime appointmentDateTime;
    private String status;
    private Integer clientId;
    private Integer stylistId;
    private Integer serviceId;

    
}
