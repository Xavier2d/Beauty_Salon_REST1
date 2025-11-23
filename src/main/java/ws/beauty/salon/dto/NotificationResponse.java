package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotificationResponse {
    
    @JsonProperty("id notification")
    private Integer id;

    @JsonProperty("message")
    private String message;

    @JsonProperty("sent via")
    private String sentVia;

    @JsonProperty("sent at")
    private LocalDateTime sentAt;
}
