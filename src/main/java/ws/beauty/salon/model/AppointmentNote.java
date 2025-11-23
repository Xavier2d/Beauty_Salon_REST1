package ws.beauty.salon.model;


import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Getter
@Setter
@Entity
@Table(name = "appointment_notes")
public class AppointmentNote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Integer idNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_appointment", nullable = false)
    private Appointment appointment;

    @Column(name = "note_text",nullable = false)
    private String noteText;
}
