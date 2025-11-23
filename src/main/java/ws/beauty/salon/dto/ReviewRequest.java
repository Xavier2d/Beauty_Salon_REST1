package ws.beauty.salon.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReviewRequest {

    private Integer idClient;
    private Integer idService;
    @NotBlank
    private String comment;

    //private Integer rating;

    //@NotBlank
    //private String sentiment;
}
