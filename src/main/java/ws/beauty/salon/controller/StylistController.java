package ws.beauty.salon.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ws.beauty.salon.dto.StylistRequest;
import ws.beauty.salon.dto.StylistResponse;
import ws.beauty.salon.model.Stylist;
import ws.beauty.salon.service.StylistService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stylists")
@Tag(name = "Stylists", description = "Provides methods for managing stylists")
public class StylistController {

    @Autowired
    private StylistService service;

    @Autowired
    private ModelMapper modelMapper;


    @Operation(summary = "Get all stylists")
    @ApiResponse(responseCode = "200", description = "Found stylists", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StylistResponse.class)))
    })
    @GetMapping
    public List<StylistResponse> getAll() {
        return service.findAll();
    }

    /*@Operation(summary = "Get all stylists with pagination")
    @ApiResponse(responseCode = "200", description = "Found stylists with pagination", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StylistResponse.class)))
    })
    @GetMapping(value = "pagination", params = {"page", "pageSize"})
    public List<StylistResponse> getAllPaginated(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        return service.findAllPaginated(page, pageSize);
    }*/

    @Operation(summary = "Get a stylist by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stylist found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = StylistResponse.class))
            }),
            @ApiResponse(responseCode = "404", description = "Stylist not found", content = @Content)
    })
    @GetMapping("/{id}")
    public StylistResponse getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new stylist")
    @ApiResponse(responseCode = "200", description = "Registered stylist", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StylistResponse.class))
    })
    @PostMapping
    public ResponseEntity<StylistResponse> create(@Valid @RequestBody StylistRequest request) {
        StylistResponse saved = service.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a stylist")
    @ApiResponse(responseCode = "200", description = "Updated stylist", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = StylistResponse.class))
    })
    @PutMapping("/{id}")
    public StylistResponse update(@PathVariable Integer id, @Valid @RequestBody StylistRequest request) {
        return service.update(id, request);
    }

    /*@Operation(summary = "Delete a stylist")
    @ApiResponse(responseCode = "204", description = "Stylist deleted", content = @Content)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }*/

    // CONSULTAS ESPECIALIZADAS

    @Operation(summary = "Get stylists by specialty")
    @ApiResponse(responseCode = "200", description = "Found stylists by specialty", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StylistResponse.class)))
    })
    @GetMapping("/specialty/{specialty}")
    public List<StylistResponse> getBySpecialty(@PathVariable String specialty) {
        return service.getBySpecialty(specialty);
    }

    @Operation(summary = "Get available stylists")
    @ApiResponse(responseCode = "200", description = "Found available stylists", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = StylistResponse.class)))
    })
    @GetMapping("/available")
    public List<StylistResponse> getAvailable() {
        return service.getAvailableStylists();
    }

    public Stylist convertToEntity(StylistRequest request) {
        return modelMapper.map(request, Stylist.class);
    }

    public StylistResponse convertToResponse(Stylist stylist) {
        return modelMapper.map(stylist, StylistResponse.class);
    }
}


