package ws.beauty.salon.controller;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cws.beauty.salon.ClientRequest;
import ws.beauty.salon.ClientResponse;
import cws.beauty.salon.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
@Tag(
    name = "Clients",
    description = "Controller for managing salon clients"
)

public class ClientController {

    private final ClientService clientService;

   
    @GetMapping
    public ResponseEntity<List<ClientResponse>> findAll(
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "size", required = false) Integer size) {

        if (page != null && size != null) {
            return ResponseEntity.ok(clientService.findAll(page, size));
        }
        return ResponseEntity.ok(clientService.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClientResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest request) {
        ClientResponse created = clientService.create(request);
        return ResponseEntity.ok(created);
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<ClientResponse> update(
            @PathVariable Integer id,
            @Valid @RequestBody ClientRequest request) {

        ClientResponse updated = clientService.update(id, request);
        return ResponseEntity.ok(updated);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

 
    @GetMapping("/by-email")
    public ResponseEntity<ClientResponse> findByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(clientService.findByEmail(email));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientResponse>> searchByName(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(clientService.searchByName(keyword));
    }


    @GetMapping("/by-registration-range")
    public ResponseEntity<List<ClientResponse>> findByRegistrationDateRange(
            @RequestParam("start")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        return ResponseEntity.ok(clientService.findByRegistrationDateRange(start, end));
    }
}

