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
public class ReviewResponse {
    @JsonProperty("id review")
    private Integer idReview;

    // Solo el ID del cliente
    @JsonProperty("id client")
    private Integer idClient;

    // Solo el ID del servicio
    @JsonProperty("id service")
    private Integer idService;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("rating")
    private Integer rating;

    @JsonProperty("sentiment")
    private String sentiment;
    @JsonProperty("first name")
    private String firstName;
    
    @JsonProperty("service name")
    private String serviceName;
 // palabras clave
    @JsonProperty("key phrases")
    private String keyPhrases;

}
