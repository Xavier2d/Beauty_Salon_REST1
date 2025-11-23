package ws.beauty.salon.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceCategoryResponse {
    Long idCategory;
    String categoryName;
    String description;
}