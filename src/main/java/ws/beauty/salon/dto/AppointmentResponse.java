package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AppointmentResponse {
    Integer idAppointment;
    LocalDateTime appointmentDatetime;
    String status;
    Integer idClient;
    Integer idStylist;
    Integer idService;
}
