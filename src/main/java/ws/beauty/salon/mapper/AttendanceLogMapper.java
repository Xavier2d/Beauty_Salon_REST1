package ws.beauty.salon.mapper;

import java.time.LocalDateTime;

import ws.beauty.salon.dto.AttendanceLogRequest;
import ws.beauty.salon.dto.AttendanceLogResponse;
import ws.beauty.salon.model.AttendanceLog;
import ws.beauty.salon.model.Stylist;

public class AttendanceLogMapper {

    private AttendanceLogMapper() {}

    public static AttendanceLogResponse toResponse(AttendanceLog entity) {
        if (entity == null) return null;

        AttendanceLogResponse res = new AttendanceLogResponse();
        res.setId(entity.getId());
        res.setStylistId(entity.getStylist() != null ? entity.getStylist().getId() : null);
        res.setStylistName(entity.getStylist() != null
                ? entity.getStylist().getFirstName() + " " + entity.getStylist().getLastName()
                : null);
        res.setCheckIn(entity.getCheckIn());
        res.setCheckOut(entity.getCheckOut());
        return res;
    }

    public static AttendanceLog toEntity(AttendanceLogRequest dto, Stylist stylist) {
        if (dto == null) return null;

        AttendanceLog entity = new AttendanceLog();
        entity.setStylist(stylist);
        entity.setCheckIn(dto.getCheckIn() != null ? dto.getCheckIn() : LocalDateTime.now());
        entity.setCheckOut(dto.getCheckOut());
        return entity;
    }

    public static void copyToEntity(AttendanceLogRequest dto, AttendanceLog entity, Stylist stylist) {
        if (dto == null || entity == null) return;
        entity.setStylist(stylist);
        entity.setCheckIn(dto.getCheckIn() != null ? dto.getCheckIn() : entity.getCheckIn());
        entity.setCheckOut(dto.getCheckOut());
    }

    }
