package ws.beauty.salon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.beauty.salon.dto.StylistServiceRequest;
import ws.beauty.salon.dto.StylistServiceResponse;
import ws.beauty.salon.service.StylistServiceService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stylist-services")
@Tag(name = "Stylist Services", description = "Manage relations between stylists and services")
@RequiredArgsConstructor
public class StylistServiceController {

    private final StylistServiceService service;

    @Operation(summary = "Get all stylist-service relations")
    @ApiResponse(responseCode = "200", description = "List of relations found",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StylistServiceResponse.class))))
    @GetMapping
    public List<StylistServiceResponse> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get relation by ID")
    @ApiResponse(responseCode = "200", description = "Relation found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = StylistServiceResponse.class)))
    @GetMapping("/{id}")
    public StylistServiceResponse getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @Operation(summary = "Get all services for a given stylist")
    @GetMapping("/stylist/{stylistId}")
    public List<StylistServiceResponse> getByStylist(@PathVariable Integer stylistId) {
        return service.findByStylist(stylistId);
    }

    @Operation(summary = "Get all stylists for a given service")
    @GetMapping("/service/{serviceId}")
    public List<StylistServiceResponse> getByService(@PathVariable Integer serviceId) {
        return service.findByService(serviceId);
    }

    @Operation(summary = "Create a new stylist-service relation")
    @PostMapping
    public ResponseEntity<StylistServiceResponse> create(@RequestBody StylistServiceRequest request) {
        StylistServiceResponse saved = service.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a stylist-service relation")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}

