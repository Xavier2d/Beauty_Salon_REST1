package ws.beauty.salon.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import ws.beauty.salon.dto.NotificationRequest;
import ws.beauty.salon.dto.NotificationResponse;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Notification;

@Component
public class NotificationMapper {
     private NotificationMapper() {}

    // 🔹 Convierte entidad → respuesta
    public static NotificationResponse toResponse(Notification entity) {
        if (entity == null)
            return null;

        NotificationResponse response = new NotificationResponse();
        response.setId(entity.getId());
        response.setMessage(entity.getMessage());
        response.setSentVia(entity.getSentVia());
        response.setSentAt(entity.getSentAt() != null ? entity.getSentAt().atStartOfDay() : LocalDateTime.now());
        return response;
    }

    // 🔹 Convierte request → entidad
    public static Notification toEntity(NotificationRequest dto, Client client) {
        if (dto == null)
            return null;

        Notification notification = new Notification();
        notification.setClient(client);
        notification.setMessage(dto.getMessage());
        notification.setSentVia(dto.getSentVia());
        notification.setSentAt(dto.getSentAt() != null ? dto.getSentAt().toLocalDate() : LocalDateTime.now().toLocalDate());
        return notification;
    }

    // 🔹 Copia datos de request a entidad existente
    public static void copyToEntity(NotificationRequest dto, Notification entity, Client client) {
        if (dto == null || entity == null)
            return;
        entity.setClient(client);
        entity.setMessage(dto.getMessage());
        entity.setSentVia(dto.getSentVia());
        entity.setSentAt(dto.getSentAt() != null ? dto.getSentAt().toLocalDate() : LocalDateTime.now().toLocalDate());
    }
}
