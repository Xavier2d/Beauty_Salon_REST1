package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ws.beauty.salon.dto.RescheduleRequestRequest;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.mapper.RescheduleRequestMapper;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.RescheduleRequest;
import ws.beauty.salon.repository.AppointmentRepository;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.repository.RescheduleRequestRepository;

import java.util.List;
@SuppressWarnings("null")
@Service
@Transactional
public class RescheduleRequestServiceImpl implements RescheduleRequestService {

    @Autowired
    private RescheduleRequestRepository repository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<RescheduleRequestResponse> findAll() {
        return repository.findAll().stream()
                .map(RescheduleRequestMapper::toResponse)
                .toList();
    }

    @Override
    public RescheduleRequestResponse findById(Integer id) {
        RescheduleRequest entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reschedule Request not found: " + id));
        return RescheduleRequestMapper.toResponse(entity);
    }

    @Override
    public RescheduleRequestResponse create(RescheduleRequestRequest request) {
        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        RescheduleRequest entity = RescheduleRequestMapper.toEntity(request, appointment, client);
        return RescheduleRequestMapper.toResponse(repository.save(entity));
    }

    @Override
    public RescheduleRequestResponse update(Integer id, RescheduleRequestRequest request) {
        RescheduleRequest existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reschedule Request not found: " + id));

        Appointment appointment = appointmentRepository.findById(request.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found"));

        RescheduleRequestMapper.copyToEntity(request, existing, appointment, client);
        return RescheduleRequestMapper.toResponse(repository.save(existing));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Reschedule Request not found: " + id);
        repository.deleteById(id);
    }

    @Override
    public List<RescheduleRequestResponse> findByClient(Integer clientId) {
        return repository.findByClientId(clientId).stream()
                .map(RescheduleRequestMapper::toResponse)
                .toList();
    }

    @Override
    public List<RescheduleRequestResponse> findByStatus(String status) {
        return repository.findByStatus(status).stream()
                .map(RescheduleRequestMapper::toResponse)
                .toList();
    }
}

