package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.AppointmentRequest;
import ws.beauty.salon.dto.AppointmentResponse;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.Stylist;

public class AppointmentMapper {

    // 🔹 Convierte entidad a DTO de respuesta
    public static AppointmentResponse toResponse(Appointment appointment) {
        if (appointment == null) return null;

        return AppointmentResponse.builder()
                .idAppointment(appointment.getId())
                .appointmentDatetime(appointment.getAppointmentDateTime())
                .status(appointment.getStatus())
                .idClient(appointment.getClient() != null ? appointment.getClient().getId() : null)
                .idStylist(appointment.getStylist() != null ? appointment.getStylist().getId() : null)
                .idService(appointment.getService() != null ? appointment.getService().getId() : null)
                .build();
    }

    // 🔹 Convierte DTO de request a entidad
    public static Appointment toEntity(
            AppointmentRequest req,
            Client client,
            Stylist stylist,
            Service service
    ) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(req.getAppointmentDateTime());
        appointment.setStatus(req.getStatus());
        appointment.setClient(client);
        appointment.setStylist(stylist);
        appointment.setService(service);
        return appointment;
    }

    // 🔹 Copia valores para actualización
    public static void copyToEntity(
            AppointmentRequest req,
            Appointment existing,
            Client client,
            Stylist stylist,
            Service service
    ) {
        existing.setAppointmentDateTime(req.getAppointmentDateTime());
        existing.setStatus(req.getStatus());
        existing.setClient(client);
        existing.setStylist(stylist);
        existing.setService(service);
    }
}
