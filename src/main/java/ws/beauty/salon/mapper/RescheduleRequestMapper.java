package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.RescheduleRequestRequest;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.RescheduleRequest;

import java.time.LocalDateTime;

public final class RescheduleRequestMapper {

    public static RescheduleRequestResponse toResponse(RescheduleRequest entity) {
        if (entity == null) return null;

        return RescheduleRequestResponse.builder()
                .idRequest(entity.getIdRequest())
                .appointmentId(entity.getAppointment() != null ? entity.getAppointment().getId() : null)
                .clientId(entity.getClient() != null ? entity.getClient().getId() : null)
                .requestedDate(entity.getRequestedDate())
                .status(entity.getStatus())
                .reason(entity.getReason())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public static RescheduleRequest toEntity(RescheduleRequestRequest dto, Appointment appointment, Client client) {
        if (dto == null) return null;
        RescheduleRequest entity = new RescheduleRequest();
        entity.setAppointment(appointment);
        entity.setClient(client);
        entity.setRequestedDate(dto.getRequestedDate());
        entity.setStatus(dto.getStatus());
        entity.setReason(dto.getReason());
        entity.setCreatedAt(LocalDateTime.now());
        return entity;
    }

    public static void copyToEntity(RescheduleRequestRequest dto, RescheduleRequest entity, Appointment appointment, Client client) {
        if (dto == null || entity == null) return;
        if (appointment != null) entity.setAppointment(appointment);
        if (client != null) entity.setClient(client);
        entity.setRequestedDate(dto.getRequestedDate());
        entity.setStatus(dto.getStatus());
        entity.setReason(dto.getReason());
    }
}

