package ws.beauty.salon.graphql;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.AttendanceLogRequest;
import ws.beauty.salon.dto.AttendanceLogResponse;
import ws.beauty.salon.service.AttendanceLogService;

@Controller
public class AttendanceLogGraphql {

    @Autowired
    private AttendanceLogService service;

    // Obtener todos los registros
    @QueryMapping
    public List<AttendanceLogResponse> findAllAttendanceLogs() {
        return service.findAll();
    }

    // Obtener todos paginados
    @QueryMapping
    public List<AttendanceLogResponse> findAllPaged(
            @Argument int page,
            @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    // Buscar por ID
    @QueryMapping
    public AttendanceLogResponse findAttendanceById(@Argument Integer idAttendance) {
        return service.findById(idAttendance);
    }

    // Buscar por estilista
    @QueryMapping
    public List<AttendanceLogResponse> findByStylistId(@Argument Integer stylistId) {
        return service.findByStylistId(stylistId);
    }

    // Buscar entre fechas
    @QueryMapping
    public List<AttendanceLogResponse> findByCheckInBetween(
            @Argument LocalDateTime start,
            @Argument LocalDateTime end) {
        return service.findByCheckInBetween(start, end);
    }

    // Contar registros por estilista
    @QueryMapping
    public long countByStylistId(@Argument Integer stylistId) {
        return service.countByStylistId(stylistId);
    }

    // Contar registros por rango de fechas
    @QueryMapping
    public long countByCheckInBetween(
            @Argument LocalDateTime start,
            @Argument LocalDateTime end) {
        return service.countByCheckInBetween(start, end);
    }

    // Verificar si hay asistencia abierta
    @QueryMapping
    public boolean hasOpenAttendance(@Argument Integer stylistId) {
        return service.hasOpenAttendance(stylistId);
    }

    // Crear nuevo registro
    @MutationMapping
    public AttendanceLogResponse createAttendance(@Valid @Argument AttendanceLogRequest request) {
        return service.create(request);
    }

    // Actualizar registro
    @MutationMapping
    public AttendanceLogResponse updateAttendance(
            @Argument Integer idAttendance,
            @Valid @Argument AttendanceLogRequest request) {
        return service.update(idAttendance, request);
    }

    // Eliminar registro
    @MutationMapping
    public Boolean deleteAttendance(@Argument Integer idAttendance) {
        service.delete(idAttendance);
        return true;
    }

    // Cerrar asistencia abierta
    @MutationMapping
    public AttendanceLogResponse closeAttendance(@Argument Integer stylistId) {
        return service.closeAttendance(stylistId);
    }
}

