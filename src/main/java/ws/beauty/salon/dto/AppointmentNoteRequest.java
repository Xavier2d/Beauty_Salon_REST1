package ws.beauty.salon.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class AppointmentNoteRequest {
    private Integer idAppointment;
    @NotBlank
    @Size(max = 500)
    private String noteText;
}
