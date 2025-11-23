package ws.beauty.salon.service;

import java.util.List;
import ws.beauty.salon.dto.StylistRequest;
import ws.beauty.salon.dto.StylistResponse;
public interface StylistService {
    List<StylistResponse> findAll();
    StylistResponse findById(Integer id);
    StylistResponse create(StylistRequest request);
    StylistResponse update(Integer id, StylistRequest request);
    List<StylistResponse> getBySpecialty(String specialty);
    List<StylistResponse> getAvailableStylists();
}