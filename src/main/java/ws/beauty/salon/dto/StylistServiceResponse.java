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
public class StylistServiceResponse {
    @JsonProperty("id_stylist")
    private Integer idStylist;
    private String stylistName;
    @JsonProperty("id_service")
    private Integer idService; 
    private String serviceName;


}
