package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ws.beauty.salon.dto.PriceHistoryRequest;
import ws.beauty.salon.dto.PriceHistoryResponse;
import ws.beauty.salon.mapper.PriceHistoryMapper;
import ws.beauty.salon.model.PriceHistory;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.User;
import ws.beauty.salon.repository.PriceHistoryRepository;
import ws.beauty.salon.repository.ServiceRepository;
import ws.beauty.salon.repository.UserRepository;

import java.util.List;

@SuppressWarnings("null")
@org.springframework.stereotype.Service
@Transactional
public class PriceHistoryServiceImpl implements PriceHistoryService {

    @Autowired
    private PriceHistoryRepository repository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PriceHistoryResponse> findAll() {
        return repository.findAll().stream()
                .map(PriceHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public PriceHistoryResponse findById(Integer id) {
        PriceHistory entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price history not found: " + id));
        return PriceHistoryMapper.toResponse(entity);
    }

    @Override
    public PriceHistoryResponse create(PriceHistoryRequest request) {
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        User user = userRepository.findById(request.getChangedById())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PriceHistory entity = PriceHistoryMapper.toEntity(request, service, user);
        return PriceHistoryMapper.toResponse(repository.save(entity));
    }

    @Override
    public PriceHistoryResponse update(Integer id, PriceHistoryRequest request) {
        PriceHistory existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Price history not found: " + id));

        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        User user = userRepository.findById(request.getChangedById())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        PriceHistoryMapper.copyToEntity(request, existing, service, user);
        return PriceHistoryMapper.toResponse(repository.save(existing));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Price history not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<PriceHistoryResponse> findByService(Integer serviceId) {
        return repository.findByServiceId(serviceId).stream()
                .map(PriceHistoryMapper::toResponse)
                .toList();
    }

    @Override
    public List<PriceHistoryResponse> findByUser(Integer userId) {
        return repository.findByChangedByUser(userId).stream()
                .map(PriceHistoryMapper::toResponse)
                .toList();
    }
}
