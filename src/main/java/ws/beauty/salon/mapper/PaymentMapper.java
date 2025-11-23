package ws.beauty.salon.mapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ws.beauty.salon.dto.PaymentRequest;
import ws.beauty.salon.dto.PaymentResponse;
import ws.beauty.salon.model.Appointment;
import ws.beauty.salon.model.Payment;

public class PaymentMapper {
   //Convierte entidad a respuesta (Payment → PaymentResponse)
    public static PaymentResponse toResponse(Payment payment) {
        if (payment == null)
            return null;

        return PaymentResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount().doubleValue())
                .paymentDate(payment.getPaymentDate() != null ? payment.getPaymentDate() : LocalDateTime.now())
                .appointmentId(payment.getAppointment() != null ? payment.getAppointment().getId() : null)
                .build();
    }

    //Convierte request a entidad (PaymentRequest → Payment)
    public static Payment toEntity(PaymentRequest dto, Appointment appointment) {
        if (dto == null)
            return null;
        return Payment.builder()
                .amount(BigDecimal.valueOf(dto.getAmount()))
                .paymentDate(dto.getPaymentDate() != null ? dto.getPaymentDate() : LocalDateTime.now())
                .appointment(appointment) // 🔸 agrega la relación
                .build();
    }

    //Copia datos de DTO a entidad existente (actualización)
    public static void copyToEntity(PaymentRequest dto, Payment entity, Appointment appointment) {
        if (dto == null || entity == null)
            return;
        entity.setAmount(BigDecimal.valueOf(dto.getAmount()));
        entity.setPaymentDate(dto.getPaymentDate() != null ? dto.getPaymentDate() : LocalDateTime.now());
        entity.setAppointment(appointment); // 🔸 actualiza la relación
    }

}
