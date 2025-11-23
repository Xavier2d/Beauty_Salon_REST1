package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import ws.beauty.salon.dto.StylistServiceRequest;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.mapper.StylistServiceMapper;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.model.StylistService;
import ws.beauty.salon.repository.ServiceRepository;
import ws.beauty.salon.repository.StylistRepository;
import ws.beauty.salon.repository.StylistServiceRepository;

import java.util.List;
@SuppressWarnings("null")
@org.springframework.stereotype.Service
@Transactional
@RequiredArgsConstructor
public class StylistServiceServiceImpl implements StylistServiceService {

    private final StylistServiceRepository repository;
    private final StylistRepository stylistRepository;
    private final ServiceRepository serviceRepository;

    @Override
    public List<StylistServiceResponse> findAll() {
        return repository.findAll().stream()
                .map(StylistServiceMapper::toResponse)
                .toList();
    }

    @Override
    public StylistServiceResponse findById(Integer id) {
        StylistService ss = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Relation not found with ID " + id));
        return StylistServiceMapper.toResponse(ss);
    }

    @Override
    public List<StylistServiceResponse> findByStylist(Integer stylistId) {
        return repository.findByStylistId(stylistId).stream()
                .map(StylistServiceMapper::toResponse)
                .toList();
    }

    @Override
    public List<StylistServiceResponse> findByService(Integer serviceId) {
        return repository.findByServiceId(serviceId).stream()
                .map(StylistServiceMapper::toResponse)
                .toList();
    }

    @Override
    public StylistServiceResponse create(StylistServiceRequest request) {
        Stylist stylist = stylistRepository.findById(request.getStylistId())
                .orElseThrow(() -> new EntityNotFoundException("Stylist not found"));
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        StylistService entity = StylistServiceMapper.toEntity(stylist, service);
        return StylistServiceMapper.toResponse(repository.save(entity));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Relation not found with ID " + id);
        }
        repository.deleteById(id);
    }
}
