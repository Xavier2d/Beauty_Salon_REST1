package ws.beauty.salon.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.ServiceRequest;
import ws.beauty.salon.dto.ServiceResponse;
import ws.beauty.salon.mapper.ServiceMapper;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.model.ServiceCategory;
import ws.beauty.salon.repository.ServiceCategoryRepository;
import ws.beauty.salon.repository.ServiceRepository;

@SuppressWarnings("null")
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService{
     private final ServiceRepository repository;
    private final ServiceCategoryRepository categoryRepository;

    @Override
    public List<ServiceResponse> findAll() {
        return repository.findAll().stream()
                .map(ServiceMapper::toResponse)
                .toList();
    }

    @Override
    public List<ServiceResponse> findAll(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Service> pageResult = repository.findAll(pageReq);
        return pageResult.getContent().stream()
                .map(ServiceMapper::toResponse)
                .toList();
    }

    @Override
    public ServiceResponse findById(Integer id) {
        Service service = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found: " + id));
        return ServiceMapper.toResponse(service);
    }

    @Override
    public ServiceResponse create(ServiceRequest req) {
        ServiceCategory category = null;
        if (req.getCategoryId() != null) {
            category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found: " + req.getCategoryId()));
        }

        Service entity = ServiceMapper.toEntity(req, category);
        Service saved = repository.save(entity);
        return ServiceMapper.toResponse(saved);
    }

    @Override
    public ServiceResponse update(Integer id, ServiceRequest req) {
        Service existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Service not found: " + id));

        ServiceCategory category = null;
        if (req.getCategoryId() != null) {
            category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new EntityNotFoundException("Category not found: " + req.getCategoryId()));
        }

        ServiceMapper.copyToEntity(req, existing, category);
        Service saved = repository.save(existing);
        return ServiceMapper.toResponse(saved);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Service not found: " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public List<ServiceResponse> findByServiceName(String name) {
        return repository.findByServiceNameContainingIgnoreCase(name)
                .stream().map(ServiceMapper::toResponse)
                .toList();
    }

    @Override
    public List<ServiceResponse> findByCategoryId(Integer categoryId) {
        return repository.findByCategoryId(categoryId)
                .stream().map(ServiceMapper::toResponse)
                .toList();
    }

}
