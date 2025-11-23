package ws.beauty.salon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StylistResponse {

    @JsonProperty("id stylist")
    private Integer id;

    @JsonProperty("first name")
    private String firstName;

    @JsonProperty("last name")
    private String lastName;

    @JsonProperty("specialty")
    private String specialty;

    @JsonProperty("work schedule")
    private String workSchedule;

    @JsonProperty("available")
    private Boolean available;
}
