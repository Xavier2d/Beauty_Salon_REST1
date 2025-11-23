package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.PriceHistoryRequest;
import ws.beauty.salon.dto.PriceHistoryResponse;
import ws.beauty.salon.model.PriceHistory;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.User;

import java.time.LocalDateTime;

public final class PriceHistoryMapper {

    private PriceHistoryMapper() {}

    public static PriceHistoryResponse toResponse(PriceHistory entity) {
        if (entity == null) return null;

        return PriceHistoryResponse.builder()
                .idPrice(entity.getIdPrice())
                .idService(entity.getService() != null ? entity.getService().getId() : null)
                .serviceName(entity.getService() != null ? entity.getService().getServiceName() : null)
                .oldPrice(entity.getOldPrice())
                .newPrice(entity.getNewPrice())
                .changedAt(entity.getChangedAt())
                .changedById(entity.getUser() != null ? entity.getUser().getId() : null)
                .changedByName(entity.getUser() != null ? entity.getUser().getUsername() : null)
                .build();
    }

    public static PriceHistory toEntity(PriceHistoryRequest dto, Service service, User user) {
        if (dto == null) return null;
        PriceHistory entity = new PriceHistory();
        entity.setService(service);
        entity.setOldPrice(dto.getOldPrice());
        entity.setNewPrice(dto.getNewPrice());
        entity.setUser(user);
        entity.setChangedAt(LocalDateTime.now());
        return entity;
    }

    public static void copyToEntity(PriceHistoryRequest dto, PriceHistory entity, Service service, User user) {
        if (dto == null || entity == null) return;
        entity.setService(service != null ? service : entity.getService());
        entity.setOldPrice(dto.getOldPrice());
        entity.setNewPrice(dto.getNewPrice());
        entity.setUser(user != null ? user : entity.getUser());
        entity.setChangedAt(LocalDateTime.now());
    }
}
