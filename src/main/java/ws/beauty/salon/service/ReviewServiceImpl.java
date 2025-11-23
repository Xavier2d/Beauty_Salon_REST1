package ws.beauty.salon.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import ws.beauty.salon.dto.ReviewRequest;
import ws.beauty.salon.dto.ReviewResponse;
import ws.beauty.salon.mapper.ReviewMapper;
import ws.beauty.salon.model.Client;
import ws.beauty.salon.model.Review;
import ws.beauty.salon.model.Service;
import ws.beauty.salon.repository.ClientRepository;
import ws.beauty.salon.repository.ReviewRepository;
import ws.beauty.salon.repository.ServiceRepository;
import java.util.List;
@SuppressWarnings("null")
@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final CognitiveService cognitiveService;

    @Override
    public List<ReviewResponse> findAllPaginated(int page, int pageSize) {
        PageRequest pageReq = PageRequest.of(page, pageSize);
        Page<Review> reviews = repository.findAll(pageReq);
        return reviews.getContent().stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    @Override
    public ReviewResponse findById(Integer id) {
        Review review = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found: " + id));
        return ReviewMapper.toResponse(review);
    }

    @Override
    public ReviewResponse create(ReviewRequest request) {

        Client client = clientRepository.findById(request.getIdClient())
                .orElseThrow(() -> new EntityNotFoundException("Client not found: " + request.getIdClient()));

        Service service = serviceRepository.findById(request.getIdService())
                .orElseThrow(() -> new EntityNotFoundException("Service not found: " + request.getIdService()));

        // Crear entidad con Mapper
        Review review = ReviewMapper.toEntity(request, client, service);

        // Azure Sentiment
        String sentiment = cognitiveService.analyzeSentiment(request.getComment());
        review.setSentiment(sentiment);

        // Azure Key phrases
        List<String> keyPhrases = cognitiveService.extractKeyPhrases(request.getComment());
        review.setKey_phrases(String.join(", ", keyPhrases));

        // Rating basado en sentimiento
        review.setRating(convertSentimentToRating(sentiment));

        // Guardar
        Review saved = repository.save(review);

        return ReviewMapper.toResponse(saved);
    }

    @Override
    public ReviewResponse update(Integer id, ReviewRequest request) {

        Review existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Review not found: " + id));

        Client client = null;
        Service service = null;

        if (request.getIdClient() != null) {
            client = clientRepository.findById(request.getIdClient())
                    .orElseThrow(() -> new EntityNotFoundException("Client not found: " + request.getIdClient()));
        }

        if (request.getIdService() != null) {
            service = serviceRepository.findById(request.getIdService())
                    .orElseThrow(() -> new EntityNotFoundException("Service not found: " + request.getIdService()));
        }

        // Actualizar con Mapper
        ReviewMapper.copyToEntity(request, existing, client, service);

        // Recalcular sentimientos si cambia el comentario
        if (request.getComment() != null) {
            String sentiment = cognitiveService.analyzeSentiment(request.getComment());
            existing.setSentiment(sentiment);
            existing.setRating(convertSentimentToRating(sentiment));

            List<String> keyPhrases = cognitiveService.extractKeyPhrases(request.getComment());
            existing.setKey_phrases(String.join(", ", keyPhrases));
        }

        return ReviewMapper.toResponse(repository.save(existing));
    }

    @Override
    public List<ReviewResponse> findByClientId(Integer clientId) {
        return repository.findByClientId(clientId).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> findByServiceId(Integer serviceId) {
        return repository.findByServiceId(serviceId).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> findBySentiment(String sentiment) {
        return repository.findBySentiment(sentiment).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    @Override
    public List<ReviewResponse> findByRatingGreaterOrEqual(Integer rating) {
        return repository.findByRatingGreaterOrEqual(rating).stream()
                .map(ReviewMapper::toResponse)
                .toList();
    }

    private int convertSentimentToRating(String sentiment) {
        return switch (sentiment.toLowerCase()) {
            case "positive" -> 5;
            case "neutral" -> 3;
            case "negative" -> 0;
            default -> 1;
        };
    }
}
