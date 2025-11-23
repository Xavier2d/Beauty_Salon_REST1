package ws.beauty.salon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import ws.beauty.salon.dto.StylistRequest;
import ws.beauty.salon.dto.StylistResponse;
import ws.beauty.salon.mapper.StylistMapper;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.repository.StylistRepository;
@SuppressWarnings("null")
@Service
@Transactional
public class StylistServiceImpl implements StylistService {

    @Autowired
    private StylistRepository repository;

    @Override
    public List<StylistResponse> findAll() {
        return repository.findAll().stream()
                .map(StylistMapper::toResponse)
                .toList();
    }

    @Override
    public StylistResponse findById(Integer id) {
        Stylist stylist = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stylist not found: " + id));
        return StylistMapper.toResponse(stylist);
    }

    @Override
    public StylistResponse create(StylistRequest request) {
        Stylist stylist = StylistMapper.toEntity(request);
        return StylistMapper.toResponse(repository.save(stylist));
    }

    @Override
    public StylistResponse update(Integer id, StylistRequest request) {
        Stylist existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Stylist not found: " + id));
        StylistMapper.copyToEntity(request, existing);
        return StylistMapper.toResponse(repository.save(existing));
    }

    /*@Override
    public void delete(Integer id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Stylist not found: " + id);
        repository.deleteById(id);
    }*/

    @Override
    public List<StylistResponse> getBySpecialty(String specialty) {
        return repository.getStylistsBySpecialtyJPQL(specialty).stream()
                .map(StylistMapper::toResponse)
                .toList();
    }

    @Override
    public List<StylistResponse> getAvailableStylists() {
        return repository.getAvailableStylists().stream()
                .map(StylistMapper::toResponse)
                .toList();
    }

    /*@Override
    public List<StylistResponse> findAllPaginated(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Stylist> stylists = repository.findAll(pageReq);
        return stylists.getContent().stream()
                .map(StylistMapper::toResponse)
                .toList();
    }*/

}