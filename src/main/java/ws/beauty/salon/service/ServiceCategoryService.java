package ws.beauty.salon.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import ws.beauty.salon.model.ServiceCategory;
import ws.beauty.salon.repository.ServiceCategoryRepository;
@SuppressWarnings("null")
@Service
@Transactional
public class ServiceCategoryService {

    @Autowired
    private ServiceCategoryRepository repository;

    public List<ServiceCategory> getAll() {
        return repository.findAll();
    }

    public List<ServiceCategory> getAll(int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<ServiceCategory> categories = repository.findAll(pageRequest);
        return categories.getContent();
    }

    public ServiceCategory getById(Integer idCategory) {
        return repository.findById(idCategory).orElse(null);
    }

    public ServiceCategory save(ServiceCategory category) {
        return repository.save(category);
    }

    public void delete(Integer idCategory) {
        repository.deleteById(idCategory);
    }

    public List<ServiceCategory> getByCategoryName(String categoryName) {
        return repository.findByCategoryNameContainingIgnoreCase(categoryName);
    }
}

