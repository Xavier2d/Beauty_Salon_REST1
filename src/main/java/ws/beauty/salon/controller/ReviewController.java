package ws.beauty.salon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.beauty.salon.dto.ReviewRequest;
import ws.beauty.salon.dto.ReviewResponse;
import ws.beauty.salon.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
@Tag(name = "Reviews", description = "Endpoints for managing client reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // 🔹 Obtener todos los reviews
    /*@Operation(summary = "Get all reviews")
    @ApiResponse(responseCode = "200", description = "List of all reviews",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ReviewResponse.class))))
    @GetMapping
    public List<ReviewResponse> getAll() {
        return reviewService.findAll();
    }*/

    // 🔹 Obtener con paginación
    @Operation(summary = "Get paginated reviews")
    @ApiResponse(responseCode = "200", description = "List of reviews with pagination",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = ReviewResponse.class))))
    @GetMapping(value = "/pagination", params = {"page", "pageSize"})
    public List<ReviewResponse> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        return reviewService.findAllPaginated(page, pageSize);
    }

    // 🔹 Obtener por ID
    @Operation(summary = "Get a review by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ReviewResponse.class))),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    @GetMapping("/{id}")
    public ReviewResponse getById(@PathVariable Integer id) {
        return reviewService.findById(id);
    }

    // 🔹 Crear un review
    @Operation(summary = "Create a new review")
    @ApiResponse(responseCode = "201", description = "Review created successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponse.class)))
    @PostMapping
    public ResponseEntity<ReviewResponse> create(@Valid @RequestBody ReviewRequest request) {
        ReviewResponse saved = reviewService.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // 🔹 Actualizar un review
    @Operation(summary = "Update an existing review")
    @ApiResponse(responseCode = "200", description = "Review updated successfully",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ReviewResponse.class)))
    @PutMapping("/{id}")
    public ReviewResponse update(@PathVariable Integer id,@Valid @RequestBody ReviewRequest request) {
        return reviewService.update(id, request);
    }

    // 🔹 Eliminar un review
    /*@Operation(summary = "Delete a review by ID")
    @ApiResponse(responseCode = "204", description = "Review deleted successfully")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        reviewService.delete(id);
    }*/

    // ---------------------- CONSULTAS ESPECIALIZADAS ----------------------

    @Operation(summary = "Find reviews by client ID")
    @GetMapping("/client/{clientId}")
    public List<ReviewResponse> getByClientId(@PathVariable Integer clientId) {
        return reviewService.findByClientId(clientId);
    }

    @Operation(summary = "Find reviews by service ID")
    @GetMapping("/service/{serviceId}")
    public List<ReviewResponse> getByServiceId(@PathVariable Integer serviceId) {
        return reviewService.findByServiceId(serviceId);
    }

    @Operation(summary = "Find reviews by sentiment")
    @GetMapping("/sentiment/{sentiment}")
    public List<ReviewResponse> getBySentiment(@PathVariable String sentiment) {
        return reviewService.findBySentiment(sentiment);
    }

    @Operation(summary = "Find reviews with rating greater or equal to a value")
    @GetMapping("/rating/{rating}")
    public List<ReviewResponse> getByRating(@PathVariable Integer rating) {
        return reviewService.findByRatingGreaterOrEqual(rating);
    }
}
