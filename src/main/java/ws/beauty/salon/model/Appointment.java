package ws.beauty.salon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_appointment")
    @JsonProperty("id_appointment")
    private Integer id;

    @Column(name = "appointment_datetime", nullable = false)
    @JsonProperty("appointment_datetime")
    private LocalDateTime appointmentDateTime;

    @Column(name = "status", length = 20)
    @JsonProperty("status")
    private String status ;

    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    @JsonProperty("client")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_stylist", referencedColumnName = "id_stylist")
    @JsonProperty("stylist")
    private Stylist stylist;

    @ManyToOne
    @JoinColumn(name = "id_service", referencedColumnName = "id_service")
    @JsonProperty("service")
    private Service service;
}
