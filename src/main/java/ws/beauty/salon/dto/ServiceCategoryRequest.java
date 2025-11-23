package ws.beauty.salon.dto;

import lombok.Data;

@Data
public class ServiceCategoryRequest {
    private Integer idCategory;
    private String categoryName;
    private String description;
}
