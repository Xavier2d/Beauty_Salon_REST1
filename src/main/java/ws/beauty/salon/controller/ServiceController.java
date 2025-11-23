package ws.beauty.salon.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import ws.beauty.salon.dto.ServiceRequest;
import ws.beauty.salon.dto.ServiceResponse;
import ws.beauty.salon.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SuppressWarnings("null")

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @GetMapping
    @Operation(summary = "Get all services")
    @ApiResponse(responseCode = "200", description = "List of registered services.", 
        content = @Content(mediaType = "application/json", 
        array = @ArraySchema(schema = @Schema(implementation = ServiceResponse.class))))
    public List<ServiceResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all services with pagination")
    public List<ServiceResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException("Invalid pagination parameters.");
        }
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get service by ID")
    public ServiceResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create new service")
    public ResponseEntity<ServiceResponse> create(@Valid @RequestBody ServiceRequest req) {
        ServiceResponse created = service.create(req);
        return ResponseEntity.created(URI.create("/api/v1/services/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update existing service")
    public ServiceResponse update(@PathVariable Integer id, @Valid @RequestBody ServiceRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete service by ID")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get services by name")
    public List<ServiceResponse> findByServiceName(@PathVariable String name) {
        return service.findByServiceName(name);
    }

    @GetMapping("/category/{categoryId}")
    @Operation(summary = "Get services by category ID")
    public List<ServiceResponse> findByCategoryId(@PathVariable Integer categoryId) {
        return service.findByCategoryId(categoryId);
    }
}
