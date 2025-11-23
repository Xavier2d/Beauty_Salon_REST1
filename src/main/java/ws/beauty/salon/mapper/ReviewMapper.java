package ws.beauty.salon.mapper;

import ws.beauty.salon.dto.ReviewRequest;
import ws.beauty.salon.dto.ReviewResponse;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Review;
import ws.beauty.salon.model.Service;

public final class ReviewMapper {

    private ReviewMapper() {}

    // ---------- Convertir REVIEW → DTO ----------
    public static ReviewResponse toResponse(Review review) {
        if (review == null) return null;

        return ReviewResponse.builder()
                .idReview(review.getId())
                .idClient(review.getClient() != null ? review.getClient().getId() : null)
                .idService(review.getService() != null ? review.getService().getId() : null)
                .comment(review.getComment())
                .rating(review.getRating())
                .sentiment(review.getSentiment())
                .firstName(review.getClient() != null ? review.getClient().getFirstName() : null)
                .serviceName(review.getService() != null ? review.getService().getServiceName() : null)
                .keyPhrases(review.getKey_phrases())
                .build();
    }

    // ---------- Convertir DTO → ENTITY (crear) ----------
    public static Review toEntity(ReviewRequest dto, Client client, Service service) {
        if (dto == null) return null;

        Review review = new Review();
        review.setClient(client);
        review.setService(service);
        review.setComment(dto.getComment());
        return review;
    }

    // ---------- Copiar valores para UPDATE ----------
    public static void copyToEntity(ReviewRequest dto, Review review, Client client, Service service) {
        if (dto == null || review == null) return;

        review.setClient(client != null ? client : review.getClient());
        review.setService(service != null ? service : review.getService());
        review.setComment(dto.getComment());
    }
}
