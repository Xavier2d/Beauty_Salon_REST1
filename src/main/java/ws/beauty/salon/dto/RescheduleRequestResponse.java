package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RescheduleRequestResponse {
    private Integer idRequest;
    private Integer appointmentId;
    private Integer clientId;
    private LocalDateTime requestedDate;
    private String status;
    private String reason;
    private LocalDateTime createdAt;
}