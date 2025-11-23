package ws.beauty.salon.service;

import ws.beauty.salon.dto.ReviewRequest;
import ws.beauty.salon.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    //List<ReviewResponse> findAll();
    List<ReviewResponse> findAllPaginated(int page, int pageSize);
    ReviewResponse findById(Integer id);
    ReviewResponse create(ReviewRequest request);
    ReviewResponse update(Integer id, ReviewRequest request);
    //void delete(Integer id);

    // Consultas especializadas
    List<ReviewResponse> findByClientId(Integer clientId);
    List<ReviewResponse> findByServiceId(Integer serviceId);
    List<ReviewResponse> findBySentiment(String sentiment);
    List<ReviewResponse> findByRatingGreaterOrEqual(Integer rating);
}