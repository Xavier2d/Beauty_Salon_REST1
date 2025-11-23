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
public class AppointmentNoteResponse {
    @JsonProperty("id note")
    private Integer idNote;
    @JsonProperty("id appointment")
    private Integer idAppointment;
    @JsonProperty("note text")
    private String noteText;
}

