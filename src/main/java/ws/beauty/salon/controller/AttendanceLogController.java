package ws.beauty.salon.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.AttendanceLogRequest;
import ws.beauty.salon.dto.AttendanceLogResponse;
import ws.beauty.salon.service.AttendanceLogService;
@SuppressWarnings("null")
@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceLogController {
     private final AttendanceLogService service;

    @GetMapping
    public List<AttendanceLogResponse> findAll() {
        return service.findAll();
    }

    @GetMapping(value = "/pagination", params = { "page", "pageSize" })
    public List<AttendanceLogResponse> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException("Invalid pagination parameters.");
        }
        return service.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    public AttendanceLogResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<AttendanceLogResponse> create(@Valid @RequestBody AttendanceLogRequest request) {
        AttendanceLogResponse created = service.create(request);
        return ResponseEntity.created(URI.create("/api/v1/attendance/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public AttendanceLogResponse update(@PathVariable Integer id, @Valid @RequestBody AttendanceLogRequest request) {
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/stylist/{stylistId}")
    public List<AttendanceLogResponse> findByStylist(@PathVariable Integer stylistId) {
        return service.findByStylistId(stylistId);
    }

    @GetMapping("/range")
    public List<AttendanceLogResponse> findByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return service.findByCheckInBetween(start, end);
    }

    @GetMapping("/open/{stylistId}")
    public boolean hasOpenAttendance(@PathVariable Integer stylistId) {
        return service.hasOpenAttendance(stylistId);
    }

    @PutMapping("/close/{stylistId}")
    public AttendanceLogResponse closeAttendance(@PathVariable Integer stylistId) {
        return service.closeAttendance(stylistId);
    }
}
