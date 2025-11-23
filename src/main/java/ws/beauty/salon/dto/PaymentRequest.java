package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class PaymentRequest {
    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("payment date")
    private LocalDateTime paymentDate;

    @NotNull(message = "Appointment ID is required")
    @JsonProperty("id appointment")
    private Integer appointmentId;
}
