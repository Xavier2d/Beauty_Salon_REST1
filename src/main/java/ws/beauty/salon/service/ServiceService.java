package ws.beauty.salon.service;

import ws.beauty.salon.dto.ServiceRequest;
import ws.beauty.salon.dto.ServiceResponse;
import java.util.List;

public interface ServiceService {
    List<ServiceResponse> findAll();
    List<ServiceResponse> findAll(int page, int pageSize);
    ServiceResponse findById(Integer id);
    ServiceResponse create(ServiceRequest req);
    ServiceResponse update(Integer id, ServiceRequest req);
    void delete(Integer id);
    List<ServiceResponse> findByServiceName(String name);
    List<ServiceResponse> findByCategoryId(Integer categoryId);

}
