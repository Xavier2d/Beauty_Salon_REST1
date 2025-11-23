package ws.beauty.salon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.beauty.salon.dto.PriceHistoryRequest;
import ws.beauty.salon.dto.PriceHistoryResponse;
import ws.beauty.salon.service.PriceHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service-price-history")
@Tag(name = "Service Price History", description = "Manages price change history for services")
public class ServicePriceHistoryController {

    @Autowired
    private PriceHistoryService service;

    @Operation(summary = "Get all price history records")
    @ApiResponse(responseCode = "200", description = "Found records",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PriceHistoryResponse.class))))
    @GetMapping
    public List<PriceHistoryResponse> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get price history by ID")
    @ApiResponse(responseCode = "200", description = "Found record")
    @GetMapping("/{id}")
    public PriceHistoryResponse getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new price history record")
    @ApiResponse(responseCode = "201", description = "Created record")
    @PostMapping
    public ResponseEntity<PriceHistoryResponse> create(@RequestBody PriceHistoryRequest request) {
        PriceHistoryResponse saved = service.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a price history record")
    @ApiResponse(responseCode = "200", description = "Updated record")
    @PutMapping("/{id}")
    public PriceHistoryResponse update(@PathVariable Integer id, @RequestBody PriceHistoryRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete a price history record")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @Operation(summary = "Get price history by service ID")
    @ApiResponse(responseCode = "200", description = "Found records by service")
    @GetMapping("/service/{serviceId}")
    public List<PriceHistoryResponse> findByService(@PathVariable Integer serviceId) {
        return service.findByService(serviceId);
    }

    @Operation(summary = "Get price history by user ID")
    @ApiResponse(responseCode = "200", description = "Found records by user")
    @GetMapping("/user/{userId}")
    public List<PriceHistoryResponse> findByUser(@PathVariable Integer userId) {
        return service.findByUser(userId);
    }
}
