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
import ws.beauty.salon.dto.RescheduleRequestRequest;
import ws.beauty.salon.dto.RescheduleRequestResponse;
import ws.beauty.salon.service.RescheduleRequestService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reschedule-requests")
@Tag(name = "Reschedule Requests", description = "Manages appointment reschedule requests")
public class RescheduleRequestController {

    @Autowired
    private RescheduleRequestService service;

    @Operation(summary = "Get all reschedule requests")
    @ApiResponse(responseCode = "200", description = "Found requests",
            content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = RescheduleRequestResponse.class))))
    @GetMapping
    public List<RescheduleRequestResponse> getAll() {
        return service.findAll();
    }

    @Operation(summary = "Get reschedule request by id")
    @ApiResponse(responseCode = "200", description = "Found request",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RescheduleRequestResponse.class)))
    @GetMapping("/{id}")
    public RescheduleRequestResponse getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @Operation(summary = "Create a new reschedule request")
    @ApiResponse(responseCode = "201", description = "Created request")
    @PostMapping
    public ResponseEntity<RescheduleRequestResponse> create(@RequestBody RescheduleRequestRequest request) {
        RescheduleRequestResponse saved = service.create(request);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @Operation(summary = "Update a reschedule request")
    @ApiResponse(responseCode = "200", description = "Updated request")
    @PutMapping("/{id}")
    public RescheduleRequestResponse update(@PathVariable Integer id, @RequestBody RescheduleRequestRequest request) {
        return service.update(id, request);
    }

    @Operation(summary = "Delete a reschedule request")
    @ApiResponse(responseCode = "204", description = "Deleted successfully")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @Operation(summary = "Get reschedule requests by client ID")
    @ApiResponse(responseCode = "200", description = "Found requests by client")
    @GetMapping("/client/{clientId}")
    public List<RescheduleRequestResponse> findByClient(@PathVariable Integer clientId) {
        return service.findByClient(clientId);
    }

    @Operation(summary = "Get reschedule requests by status")
    @ApiResponse(responseCode = "200", description = "Found requests by status")
    @GetMapping("/status/{status}")
    public List<RescheduleRequestResponse> findByStatus(@PathVariable String status) {
        return service.findByStatus(status);
    }
}

