package ws.beauty.salon.service;

import ws.beauty.salon.dto.AppointmentNoteRequest;
import ws.beauty.salon.dto.AppointmentNoteResponse;

import java.util.List;

public interface AppointmentNoteService {
    //List<AppointmentNoteResponse> findAll();
    List<AppointmentNoteResponse> findAllPaginated(int page, int size);
    AppointmentNoteResponse findById(Integer id);
    AppointmentNoteResponse create(AppointmentNoteRequest request);
    AppointmentNoteResponse update(Integer id, AppointmentNoteRequest request);
    //void delete(Integer id);
}
