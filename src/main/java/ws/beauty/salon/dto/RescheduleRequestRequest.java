package ws.beauty.salon.dto;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RescheduleRequestRequest {
    private Integer appointmentId;
    private Integer clientId;
    private LocalDateTime requestedDate;
    private String status;
    private String reason;
}