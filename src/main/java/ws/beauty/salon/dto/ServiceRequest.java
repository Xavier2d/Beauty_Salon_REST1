package ws.beauty.salon.dto;

import java.time.Duration;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequest {
    @NotBlank(message = "Service name is required")
    @JsonProperty("service_name")
    private String serviceName;

    @JsonProperty("description")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @JsonProperty("price")
    private Double price;

    @JsonProperty("estimated_duration")
    private Duration estimatedDuration;

    @JsonProperty("id_category")
    private Integer categoryId;
}
