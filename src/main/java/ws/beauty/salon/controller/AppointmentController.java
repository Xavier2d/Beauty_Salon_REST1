package ws.beauty.salon.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ws.beauty.salon.AppointmentRequest;
import ws.beauty.salon.AppointmentResponse;
import ws.beauty.salon.AppointmentService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Tag(
    name = "Appointments",
    description = "Controller for managing salon appointments"
)

public class AppointmentController {

    private final AppointmentService appointmentService;


    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {

        if (page != null && size != null) {
            return ResponseEntity.ok(appointmentService.findAll(page, size));
        }
        return ResponseEntity.ok(appointmentService.findAll());
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

   
    @PostMapping
    public ResponseEntity<AppointmentResponse> create(@Valid @RequestBody AppointmentRequest request) {
        AppointmentResponse created = appointmentService.create(request);
        return ResponseEntity.ok(created);
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody AppointmentRequest request) {

        AppointmentResponse updated = appointmentService.update(id, request);
        return ResponseEntity.ok(updated);
    }

   
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        appointmentService.delete(id);
        return ResponseEntity.noContent().build();
    }

 
    @GetMapping("/by-client/{idClient}")
    public ResponseEntity<List<AppointmentResponse>> findByClientId(@PathVariable Integer idClient) {
        return ResponseEntity.ok(appointmentService.findByClientId(idClient));
    }


    @GetMapping("/by-stylist/{idStylist}")
    public ResponseEntity<List<AppointmentResponse>> findByStylistId(@PathVariable Integer idStylist) {
        return ResponseEntity.ok(appointmentService.findByStylistId(idStylist));
    }

 
    @GetMapping("/by-status")
    public ResponseEntity<List<AppointmentResponse>> findByStatus(
            @RequestParam("status") String status) {
        return ResponseEntity.ok(appointmentService.findByStatus(status));
    }

  
    @GetMapping("/by-date-range")
    public ResponseEntity<List<AppointmentResponse>> findByDateRange(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return ResponseEntity.ok(appointmentService.findByDateRange(start, end));
    }
}
