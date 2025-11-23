package ws.beauty.salon.service;

import ws.beauty.salon.dto.RescheduleRequestRequest;
import ws.beauty.salon.dto.RescheduleRequestResponse;

import java.util.List;

public interface RescheduleRequestService {
    List<RescheduleRequestResponse> findAll();
    RescheduleRequestResponse findById(Integer id);
    RescheduleRequestResponse create(RescheduleRequestRequest request);
    RescheduleRequestResponse update(Integer id, RescheduleRequestRequest request);
    void delete(Integer id);
    List<RescheduleRequestResponse> findByClient(Integer clientId);
    List<RescheduleRequestResponse> findByStatus(String status);
}
