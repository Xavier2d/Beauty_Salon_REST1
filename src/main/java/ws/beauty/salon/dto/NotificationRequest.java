package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {
    @NotNull(message = "Client ID is required")
    @JsonProperty("id client")
    private Integer clientId;

    @NotBlank(message = "Message is required")
    @JsonProperty("message")
    private String message;

    @NotBlank(message = "Sent via is required")
    @Pattern(regexp = "SMS|email|WhatsApp", message = "Sent via must be SMS, email, or WhatsApp")
    @JsonProperty("sent via")
    private String sentVia;

    @JsonProperty("sent at")
    private LocalDateTime sentAt;
}
