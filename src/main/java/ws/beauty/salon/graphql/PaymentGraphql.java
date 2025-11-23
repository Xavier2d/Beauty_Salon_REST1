package ws.beauty.salon.graphql;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ws.beauty.salon.dto.PaymentRequest;
import ws.beauty.salon.dto.PaymentResponse;
import ws.beauty.salon.service.PaymentService;

@Controller
public class PaymentGraphql {

    @Autowired
    private PaymentService service;

    // Consultas
    @QueryMapping
    public List<PaymentResponse> findAllPayments() {
        return service.findAll();
    }

    @QueryMapping
    public List<PaymentResponse> findAllPaymentsPaged(@Argument int page, @Argument int pageSize) {
        return service.findAll(page, pageSize);
    }

    @QueryMapping
    public PaymentResponse findPaymentById(@Argument Integer idPayment) {
        return service.findById(idPayment);
    }

    @QueryMapping
    public PaymentResponse findPaymentByAppointmentId(@Argument Integer appointmentId) {
        return service.findByAppointmentId(appointmentId);
    }

    @QueryMapping
    public List<PaymentResponse> findPaymentsByClientId(@Argument Integer clientId) {
        return service.findByClientId(clientId);
    }

    @QueryMapping
    public List<PaymentResponse> findPaymentsByStylistId(@Argument Integer stylistId) {
        return service.findByStylistId(stylistId);
    }

    @QueryMapping
    public List<PaymentResponse> findPaymentsByDateRange(
            @Argument LocalDateTime start,
            @Argument LocalDateTime end) {
        return service.findByPaymentDateBetween(start, end);
    }

    @QueryMapping
    public Double getTotalPaymentsBetweenDates(
            @Argument LocalDateTime start,
            @Argument LocalDateTime end) {
        return service.getTotalAmountBetweenDates(start, end);
    }

    @QueryMapping
    public Double getTotalPaymentsByClient(@Argument Integer clientId) {
        return service.getTotalAmountByClient(clientId);
    }

    @QueryMapping
    public Double getTotalPaymentsByStylist(@Argument Integer stylistId) {
        return service.getTotalAmountByStylist(stylistId);
    }

    @QueryMapping
    public Boolean existsPaymentByAppointment(@Argument Integer appointmentId) {
        return service.existsByAppointmentId(appointmentId);
    }

    // Mutaciones
    @MutationMapping
    public PaymentResponse createPayment(@Valid @Argument PaymentRequest request) {
        return service.create(request);
    }

    @MutationMapping
    public PaymentResponse updatePayment(@Argument Integer idPayment, @Valid @Argument PaymentRequest request) {
        return service.update(idPayment, request);
    }

}
