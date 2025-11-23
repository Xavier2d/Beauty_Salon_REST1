package ws.beauty.salon.dto;

import java.math.BigDecimal;


import lombok.Data;

@Data
public class PriceHistoryRequest {
    private Integer serviceId;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private Integer changedById;
}
