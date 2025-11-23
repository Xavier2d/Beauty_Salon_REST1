package ws.beauty.salon.service;

import ws.beauty.salon.dto.StylistServiceRequest;
import ws.beauty.salon.dto.StylistServiceResponse;

import java.util.List;

public interface StylistServiceService {
    List<StylistServiceResponse> findAll();
    StylistServiceResponse findById(Integer id);
    List<StylistServiceResponse> findByStylist(Integer stylistId);
    List<StylistServiceResponse> findByService(Integer serviceId);
    StylistServiceResponse create(StylistServiceRequest request);
    void delete(Integer id);
}
