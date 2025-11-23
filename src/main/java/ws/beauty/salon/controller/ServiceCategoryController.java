package ws.beauty.salon.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import ws.beauty.salon.dto.ServiceCategoryRequest;
import ws.beauty.salon.model.ServiceCategory;
import ws.beauty.salon.service.ServiceCategoryService;

@RestController
@RequestMapping("/api/v1/service-categories")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Service Categories", description = "Endpoints for managing service categories")
public class ServiceCategoryController {

    @Autowired
    private ServiceCategoryService service;

    @Autowired
    private ModelMapper modelMapper;

    @Operation(summary = "Get all service categories")
    @ApiResponse(responseCode = "200", description = "List of categories found",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ServiceCategory.class))))
    @GetMapping
    public List<ServiceCategory> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Get paginated service categories")
    @GetMapping("/pagination")
    public List<ServiceCategory> getAllPaginated(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int pageSize) {
        return service.getAll(page, pageSize);
    }

    @Operation(summary = "Get category by ID")
    @ApiResponse(responseCode = "200", description = "Category found",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ServiceCategory.class)))
    @GetMapping("/{idCategory}")
    public ResponseEntity<ServiceCategory> getById(@PathVariable Integer idCategory) {
        ServiceCategory category = service.getById(idCategory);
        if (category == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @Operation(summary = "Search categories by name")
    @GetMapping("/search/{categoryName}")
    public List<ServiceCategory> searchByCategoryName(@PathVariable String categoryName) {
        return service.getByCategoryName(categoryName);
    }

    @Operation(summary = "Create a new category")
    @PostMapping
    public ResponseEntity<ServiceCategoryRequest> add(@RequestBody ServiceCategoryRequest dto) {
        ServiceCategory category = convertToEntity(dto);
        ServiceCategory saved = service.save(category);
        return new ResponseEntity<>(convertToDTO(saved), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a category by ID")
    @DeleteMapping("/{idCategory}")
    public ResponseEntity<Void> delete(@PathVariable Integer idCategory) {
        service.delete(idCategory);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private ServiceCategoryRequest convertToDTO(ServiceCategory category) {
        return modelMapper.map(category, ServiceCategoryRequest.class);
    }

    private ServiceCategory convertToEntity(ServiceCategoryRequest dto) {
        return modelMapper.map(dto, ServiceCategory.class);
    }
}
