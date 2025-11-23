package ws.beauty.salon.service;

import ws.beauty.salon.dto.PriceHistoryRequest;
import ws.beauty.salon.dto.PriceHistoryResponse;

import java.util.List;

public interface PriceHistoryService {
    List<PriceHistoryResponse> findAll();
    PriceHistoryResponse findById(Integer id);
    PriceHistoryResponse create(PriceHistoryRequest request);
    PriceHistoryResponse update(Integer id, PriceHistoryRequest request);
    void delete(Integer id);
    List<PriceHistoryResponse> findByService(Integer serviceId);
    List<PriceHistoryResponse> findByUser(Integer userId);
}
