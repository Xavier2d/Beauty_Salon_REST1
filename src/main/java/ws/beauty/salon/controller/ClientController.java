package ws.beauty.salon.controller;


import java.net.URI;
import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.ClientRequest;
import ws.beauty.salon.dto.ClientResponse;

import ws.beauty.salon.service.ClientService;
@SuppressWarnings("null")
@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
@Validated
public class ClientController {
private final ClientService service;

    //  Obtener todos los clientes
    @GetMapping
    @Operation(summary = "Get all clients")
    @ApiResponse(responseCode = "200", description = "List of registered clients.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ClientResponse.class))) })
    public List<ClientResponse> findAll() {
        return service.findAll();
    }

    //  Obtener clientes con paginación
    @GetMapping(value = "/pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all clients with pagination")
    public List<ClientResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return service.findAll(page, pageSize);
    }

    //  Obtener cliente por su ID
    @GetMapping("/{id}")
    @Operation(summary = "Get client by ID")
    public ClientResponse findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    //  Crear un nuevo cliente
    @PostMapping
    @Operation(summary = "Create new client")
    public ResponseEntity<ClientResponse> create(@Valid @RequestBody ClientRequest req) {
        ClientResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/clients/" + created.getId()))
                .body(created);
    }

    //  Actualizar un cliente
    @PutMapping("/{id}")
    @Operation(summary = "Update existing client")
    public ClientResponse update(@PathVariable Integer id, @Valid @RequestBody ClientRequest req) {
        return service.update(id, req);
    }

    //  Eliminar un cliente
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client by ID")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //  Buscar clientes por correo electrónico
    @GetMapping("/email")
    @Operation(summary = "Find client by email")
    public ClientResponse findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

    //  Buscar clientes por nombre
    @GetMapping("/name")
    @Operation(summary = "Find clients by name")
    public List<ClientResponse> findByName(@RequestParam String name) {
        return service.findByName(name);
    }
}
