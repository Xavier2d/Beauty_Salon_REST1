package ws.beauty.salon.service;
import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.domain.PageRequest;

import jakarta.persistence.EntityNotFoundException;
import ws.beauty.salon.dto.NotificationRequest;
import ws.beauty.salon.dto.NotificationResponse;
import ws.beauty.salon.mapper.NotificationMapper;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.repository.NotificationRepository;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@SuppressWarnings("null")
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

     private final NotificationRepository repository;
    private final ClientRepository clientRepository;

    @Override
    public List<NotificationResponse> findAll() {
        return repository.findAll().stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Notification> notifications = repository.findAll(pageReq);
        return notifications.getContent().stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public NotificationResponse findById(Integer idNotification) {
        Notification notification = repository.findById(idNotification)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found: " + idNotification));
        return NotificationMapper.toResponse(notification);
    }

    @Override
    public NotificationResponse create(NotificationRequest request) {
        Client client = clientRepository.findById(request.getClientId())
    .orElseThrow(() -> new EntityNotFoundException("Client not found: " + request.getClientId()));
        Notification entity = NotificationMapper.toEntity(request, client);
        Notification saved = repository.save(entity);
        return NotificationMapper.toResponse(saved);
    }

    @Override
    public NotificationResponse update(Integer idNotification, NotificationRequest request) {
        Notification existing = repository.findById(idNotification)
                .orElseThrow(() -> new EntityNotFoundException("Notification not found: " + idNotification));
        Client client = clientRepository.findById(request.getClientId())
    .orElseThrow(() -> new EntityNotFoundException("Client not found: " + request.getClientId()));


        NotificationMapper.copyToEntity(request, existing, client);
        Notification saved = repository.save(existing);
        return NotificationMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer idNotification) {
        if (!repository.existsById(idNotification)) {
            throw new EntityNotFoundException("Notification not found: " + idNotification);
        }
        repository.deleteById(idNotification);
    }

    @Override
    public List<NotificationResponse> findByClientId(Integer clientId) {
        return repository.findByClientId(clientId).stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> findBySentVia(String sentVia) {
        return repository.findBySentVia(sentVia).stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public List<NotificationResponse> findBySentAtBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findBySentAtBetween(start, end).stream()
                .map(NotificationMapper::toResponse)
                .toList();
    }

    @Override
    public long countByClientId(Integer clientId) {
        return repository.countByClientId(clientId);
    }
}
