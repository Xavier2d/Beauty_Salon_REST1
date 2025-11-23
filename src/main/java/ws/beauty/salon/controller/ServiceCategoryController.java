package ws.beauty.salon.controller;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ws.beauty.salon.dto.ServiceCategoryRequest;
import ws.beauty.salon.dto.ServiceCategoryResponse;
import ws.beauty.salon.service.ServiceCategoryService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/service-categories")
@RequiredArgsConstructor
@Tag(
    name = "Service Categories",
    description = "Controller for managing service categories"
)

public class ServiceCategoryController {

    private final ServiceCategoryService serviceCategoryService;

    // GET /api/service-categories?page=0&size=10
    @GetMapping
    public ResponseEntity<List<ServiceCategoryResponse>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {

        if (page != null && size != null) {
            return ResponseEntity.ok(serviceCategoryService.findAll(page, size));
        }
        return ResponseEntity.ok(serviceCategoryService.findAll());
    }

    // GET /api/service-categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ServiceCategoryResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(serviceCategoryService.findById(id));
    }

    // POST /api/service-categories
    @PostMapping
    public ResponseEntity<ServiceCategoryResponse> create(
            @Valid @RequestBody ServiceCategoryRequest request) {

        ServiceCategoryResponse created = serviceCategoryService.create(request);
        return ResponseEntity.ok(created);
    }

    // PUT /api/service-categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ServiceCategoryResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody ServiceCategoryRequest request) {

        ServiceCategoryResponse updated = serviceCategoryService.update(id, request);
        return ResponseEntity.ok(updated);
    }

    // DELETE /api/service-categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        serviceCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/service-categories/by-name?name=...
    @GetMapping("/by-name")
    public ResponseEntity<ServiceCategoryResponse> findByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(serviceCategoryService.findByName(name));
    }

    // GET /api/service-categories/search?keyword=...
    @GetMapping("/search")
    public ResponseEntity<List<ServiceCategoryResponse>> searchByName(
            @RequestParam("keyword") String keyword) {

        return ResponseEntity.ok(serviceCategoryService.searchByName(keyword));
    }
}

