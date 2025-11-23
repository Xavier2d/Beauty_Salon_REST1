package ws.beauty.salon.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PaymentResponse {
    @JsonProperty("id payment")
    private Integer id;

    @JsonProperty("amount")
    private Double amount;

    @JsonProperty("payment date")
    private LocalDateTime paymentDate;

    @JsonProperty("id appointment")
    private Integer appointmentId;
}
