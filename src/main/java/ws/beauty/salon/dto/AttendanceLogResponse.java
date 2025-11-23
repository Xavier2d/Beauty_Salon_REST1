package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AttendanceLogResponse {
    @JsonProperty("id attendance")
    private Integer id;

    @JsonProperty("id stylist")
    private Integer stylistId;

    @JsonProperty("stylist name")
    private String stylistName;

    @JsonProperty("check in")
    private LocalDateTime checkIn;

    @JsonProperty("check out")
    private LocalDateTime checkOut;
}
