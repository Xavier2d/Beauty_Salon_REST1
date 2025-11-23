package ws.beauty.salon.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryResponse {
    private Integer idPrice;          // ID del registro de historial
    private Integer idService;        // ID del servicio
    private String serviceName;       // Nombre del servicio
    private BigDecimal oldPrice;      // Precio anterior
    private BigDecimal newPrice;      // Nuevo precio
    private LocalDateTime changedAt;  // Fecha y hora del cambio
    private Integer changedById;      // ID del usuario que hizo el cambio
    private String changedByName;     // Nombre del usuario que hizo el cambio
}
