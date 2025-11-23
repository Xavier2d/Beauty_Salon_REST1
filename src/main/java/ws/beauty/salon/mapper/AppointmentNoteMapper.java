package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.AppointmentNoteRequest;
import ws.beauty.salon.dto.AppointmentNoteResponse;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.AppointmentNote;

public final class AppointmentNoteMapper {

    private AppointmentNoteMapper() {
        // Evitar instanciación
    }

    public static AppointmentNoteResponse toResponse(AppointmentNote note) {
        if (note == null) return null;
        return AppointmentNoteResponse.builder()
                .idNote(note.getIdNote())
                .idAppointment(note.getAppointment() != null ? note.getAppointment().getId() : null)
                .noteText(note.getNoteText())
                .build();
    }

    public static AppointmentNote toEntity(AppointmentNoteRequest dto, Appointment appointment) {
        if (dto == null) return null;
        AppointmentNote note = new AppointmentNote();
        note.setAppointment(appointment);
        note.setNoteText(dto.getNoteText());
        return note;
    }

    public static void copyToEntity(AppointmentNoteRequest dto, AppointmentNote note, Appointment appointment) {
        if (dto == null || note == null) return;
        if (appointment != null) {
            note.setAppointment(appointment);
        }
        note.setNoteText(dto.getNoteText());
    }
}
