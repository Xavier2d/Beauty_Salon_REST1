package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ws.beauty.salon.dto.AppointmentNoteRequest;
import ws.beauty.salon.dto.AppointmentNoteResponse;
import ws.beauty.salon.mapper.AppointmentNoteMapper;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.AppointmentNote;
import ws.beauty.salon.repository.AppointmentNoteRepository;
import ws.beauty.salon.repository.AppointmentRepository;
//import ws.beauty.salon.service.AppointmentNoteService;

import java.util.List;
import java.util.stream.Collectors;
@SuppressWarnings("null")
@Service
@RequiredArgsConstructor
public class AppointmentNoteServiceImpl implements AppointmentNoteService {

    private final AppointmentNoteRepository noteRepository;
    private final AppointmentRepository appointmentRepository;

    /*@Override
    public List<AppointmentNoteResponse> findAll() {
        return noteRepository.findAll()
                .stream()
                .map(AppointmentNoteMapper::toResponse)
                .collect(Collectors.toList());
    }*/

    @Override
    public List<AppointmentNoteResponse> findAllPaginated(int page, int size) {
        return noteRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(AppointmentNoteMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentNoteResponse findById(Integer id) {
        AppointmentNote note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + id));
        return AppointmentNoteMapper.toResponse(note);
    }

    @Override
    public AppointmentNoteResponse create(AppointmentNoteRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getIdAppointment())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id " + request.getIdAppointment()));

        AppointmentNote note = AppointmentNoteMapper.toEntity(request, appointment);
        noteRepository.save(note);
        return AppointmentNoteMapper.toResponse(note);
    }

    @Override
    public AppointmentNoteResponse update(Integer id, AppointmentNoteRequest request) {
        AppointmentNote existing = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with id " + id));

        Appointment appointment = appointmentRepository.findById(request.getIdAppointment())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found with id " + request.getIdAppointment()));

        AppointmentNoteMapper.copyToEntity(request, existing, appointment);
        noteRepository.save(existing);
        return AppointmentNoteMapper.toResponse(existing);
    }

    /*@Override
    public void delete(Integer id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note not found with id " + id);
        }
        noteRepository.deleteById(id);
    }*/
}
