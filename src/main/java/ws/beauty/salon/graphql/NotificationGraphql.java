package ws.beauty.salon.graphql;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.NotificationRequest;
import ws.beauty.salon.dto.NotificationResponse;
import ws.beauty.salon.service.NotificationService;

@Controller
public class NotificationGraphql {

    @Autowired
    private NotificationService service;

    // Consultas
    @QueryMapping
    public List<NotificationResponse> findAllNotifications() {
        return service.findAll();
    }

    @QueryMapping
    public List<NotificationResponse> findAllNotificationsPaged(@Argument int page, @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    @QueryMapping
    public NotificationResponse findNotificationById(@Argument Integer idNotification) {
        return service.findById(idNotification);
    }

    @QueryMapping
    public List<NotificationResponse> findNotificationsByClientId(@Argument Integer clientId) {
        return service.findByClientId(clientId);
    }

    @QueryMapping
    public List<NotificationResponse> findNotificationsBySentVia(@Argument String sentVia) {
        return service.findBySentVia(sentVia);
    }

    @QueryMapping
    public List<NotificationResponse> findNotificationsBySentAtBetween(
            @Argument LocalDateTime start,
            @Argument LocalDateTime end) {
        return service.findBySentAtBetween(start, end);
    }

    // Mutaciones
    @MutationMapping
    public NotificationResponse createNotification(@Valid @Argument NotificationRequest request) {
        return service.create(request);
    }

    @MutationMapping
    public NotificationResponse updateNotification(@Argument Integer idNotification,
                                                   @Valid @Argument NotificationRequest request) {
        return service.update(idNotification, request);
    }

    @MutationMapping
    public Boolean deleteNotification(@Argument Integer idNotification) {
        service.delete(idNotification);
        return true;
    }
}

