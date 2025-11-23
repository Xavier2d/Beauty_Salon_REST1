/*package ws.beauty.salon.graphql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.AppointmentRequest;
import ws.beauty.salon.dto.AppointmentResponse;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.service.AppointmentService;

@Controller
public class AppointmentGraphql {

    @Autowired
    private AppointmentService service;

    //  Obtener todas las citas
    @QueryMapping
    public List<Appointment> findAllAppointments() {
        return service.getAll();
    }

    //  Obtener todas las citas paginadas
    @QueryMapping
    public List<Appointment> findAllAppointmentsPaginated(
            @Argument int page,
            @Argument int size) {
        return service.getAll(page, size);
    }

    //  Obtener cita por ID
    @QueryMapping
    public Appointment findAppointmentById(@Argument Integer idAppointment) {
        return service.getById(idAppointment);
    }

    //  Obtener citas por estado
    @QueryMapping
    public List<Appointment> findAppointmentsByStatus(@Argument String status) {
        return service.getByStatus(status);
    }

    //  Obtener citas por cliente
    @QueryMapping
    public List<Appointment> findAppointmentsByClient(@Argument Integer clientId) {
        return service.getByClient(clientId);
    }

    // Crear cita
    @MutationMapping
    public Appointment createAppointment(@Valid @Argument AppointmentRequest req) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(req.getAppointmentDateTime());
        appointment.setStatus(req.getStatus());
        // suponiendo que ya están seteadas las relaciones con client, stylist, service
        appointment.setStylist(req.getClientId());
        appointment.setStylistId(req.getStylistId());
        appointment.setServiceId(req.getServiceId());
        return service.save(appointment);
    }

    // 🔹 Actualizar cita
    @MutationMapping
    public Appointment updateAppointment(
            @Argument Integer idAppointment,
            @Valid @Argument AppointmentRequest req) {

        Appointment existing = service.getById(idAppointment);
        if (existing == null) {
            throw new RuntimeException("Appointment not found with id " + idAppointment);
        }

        existing.setAppointmentDateTime(req.getAppointmentDateTime());
        existing.setStatus(req.getStatus());
        existing.setClientId(req.getClientId());
        existing.setStylistId(req.getStylistId());
        existing.setServiceId(req.getServiceId());
        return service.save(existing);
    }

    // 🔹 Eliminar cita
    @MutationMapping
    public Boolean deleteAppointment(@Argument Integer idAppointment) {
        service.delete(idAppointment);
        return true;
    }
}*/
