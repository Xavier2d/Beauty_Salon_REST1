package ws.beauty.salon.graphql;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import ws.beauty.salon.dto.ReviewRequest;
import ws.beauty.salon.dto.ReviewResponse;
import ws.beauty.salon.service.ReviewService;

@Controller
public class ReviewGraphql {

    @Autowired
    private ReviewService service;

    // -------------------- Queries --------------------

    /*@QueryMapping
    public List<ReviewResponse> findAllReviews() {
        return service.findAll();
    }*/

    @QueryMapping
    public ReviewResponse findReviewById(@Argument Integer id) {
        return service.findById(id);
    }

    @QueryMapping
    public List<ReviewResponse> findReviewsByClient(@Argument Integer idClient) {
        return service.findByClientId(idClient);
    }

    @QueryMapping
    public List<ReviewResponse> findReviewsByService(@Argument Integer idService) {
        return service.findByServiceId(idService);
    }

    @QueryMapping
    public List<ReviewResponse> findReviewsBySentiment(@Argument String sentiment) {
        return service.findBySentiment(sentiment);
    }

    @QueryMapping
    public List<ReviewResponse> findReviewsByRatingGte(@Argument Integer rating) {
        return service.findByRatingGreaterOrEqual(rating);
    }

    // -------------------- Mutations --------------------

    @MutationMapping
    public ReviewResponse createReview(@Valid @Argument ReviewRequest req) {
        return service.create(req);
    }

    @MutationMapping
    public ReviewResponse updateReview(@Argument Integer id, @Valid @Argument ReviewRequest req) {
        return service.update(id, req);
    }

    /*@MutationMapping
    public Boolean deleteReview(@Argument Integer id) {
        service.delete(id);
        return true;
    }*/
}
