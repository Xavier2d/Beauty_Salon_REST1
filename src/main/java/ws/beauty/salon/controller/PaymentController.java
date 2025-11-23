package ws.beauty.salon.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ws.beauty.salon.dto.PaymentRequest;
import ws.beauty.salon.dto.PaymentResponse;
import ws.beauty.salon.service.PaymentService;

@SuppressWarnings("null")
@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

private final PaymentService service;

    // 🔹 Obtener todos los pagos
    @GetMapping
    @Operation(summary = "Get all payments")
    @ApiResponse(responseCode = "200", description = "List of registered payments.", content = {
            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PaymentResponse.class))) })
    public List<PaymentResponse> findAll() {
        return service.findAll();
    }

    // 🔹 Obtener todos los pagos con paginación
    @GetMapping(value = "/pagination", params = { "page", "pageSize" })
    @Operation(summary = "Get all payments with pagination")
    public List<PaymentResponse> findAll(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        if (page < 0 || pageSize < 0 || (page == 0 && pageSize == 0)) {
            throw new IllegalArgumentException(
                    "Invalid pagination parameters: page and pageSize cannot be negative and cannot both be 0.");
        }
        return service.findAll(page, pageSize);
    }

    // 🔹 Obtener un pago por su ID
    @GetMapping("/{idPayment}")
    @Operation(summary = "Get payment by ID")
    public PaymentResponse findById(@PathVariable Integer idPayment) {
        return service.findById(idPayment);
    }

    // 🔹 Crear un nuevo pago
    @PostMapping
    @Operation(summary = "Create new payment")
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest req) {
        PaymentResponse created = service.create(req);
        return ResponseEntity
                .created(URI.create("/api/v1/payments/" + created.getId()))
                .body(created);
    }

    // 🔹 Actualizar un pago
    @PutMapping("/{idPayment}")
    @Operation(summary = "Update existing payment")
    public PaymentResponse update(@PathVariable Integer idPayment, @Valid @RequestBody PaymentRequest req) {
        return service.update(idPayment, req);
    }

    // 🔹 Buscar pago por ID de cita
    @GetMapping("/appointment/{appointmentId}")
    @Operation(summary = "Get payment by appointment ID")
    public PaymentResponse findByAppointmentId(@PathVariable Integer appointmentId) {
        return service.findByAppointmentId(appointmentId);
    }

    // 🔹 Buscar pagos por cliente
    @GetMapping("/client/{clientId}")
    @Operation(summary = "Get payments by client ID")
    public List<PaymentResponse> findByClientId(@PathVariable Integer clientId) {
        return service.findByClientId(clientId);
    }

    // 🔹 Buscar pagos por estilista
    @GetMapping("/stylist/{stylistId}")
    @Operation(summary = "Get payments by stylist ID")
    public List<PaymentResponse> findByStylistId(@PathVariable Integer stylistId) {
        return service.findByStylistId(stylistId);
    }

    // 🔹 Buscar pagos en un rango de fechas
    @GetMapping("/dates")
    @Operation(summary = "Get payments between two dates")
    public List<PaymentResponse> findByPaymentDateBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return service.findByPaymentDateBetween(start, end);
    }

    // 🔹 Calcular total de pagos por rango de fechas
    @GetMapping("/total/dates")
    @Operation(summary = "Get total payment amount between two dates")
    public Double getTotalAmountBetweenDates(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return service.getTotalAmountBetweenDates(start, end);
    }

    // 🔹 Calcular total por cliente
    @GetMapping("/total/client/{clientId}")
    @Operation(summary = "Get total payment amount by client ID")
    public Double getTotalAmountByClient(@PathVariable Integer clientId) {
        return service.getTotalAmountByClient(clientId);
    }

    // 🔹 Calcular total por estilista
    @GetMapping("/total/stylist/{stylistId}")
    @Operation(summary = "Get total payment amount by stylist ID")
    public Double getTotalAmountByStylist(@PathVariable Integer stylistId) {
        return service.getTotalAmountByStylist(stylistId);
    }

    // 🔹 Verificar si existe un pago para una cita
    @GetMapping("/exists/{appointmentId}")
    @Operation(summary = "Check if a payment exists for an appointment ID")
    public boolean existsByAppointmentId(@PathVariable Integer appointmentId) {
        return service.existsByAppointmentId(appointmentId);
    }

}