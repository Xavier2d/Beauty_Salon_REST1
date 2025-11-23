package ws.beauty.salon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import jakarta.transaction.Transactional;
import ws.beauty.salon.repository.AppointmentRepository;
import ws.beauty.salon.model.Appointment;
@SuppressWarnings("null")
@Service
@Transactional
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public List<Appointment> getAll() {
        return repository.findAll();
    }

    public List<Appointment> getAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Appointment> appointments = repository.findAll(pageReq);
        return appointments.getContent();
    }

    public List<Appointment> getAllOrderByDate() {
        return repository.findAll(Sort.by("appointmentDateTime"));
    }

    public Appointment save(Appointment appointment) {
        return repository.save(appointment);
    }

    public Appointment getById(Integer idAppointment) {
        return repository.findById(idAppointment).orElse(null);
    }

    public void delete(Integer idAppointment) {
        repository.deleteById(idAppointment);
    }

    public List<Appointment> getByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Appointment> getByClient(Integer clientId) {
        return repository.findByClient(clientId);
    }
}
