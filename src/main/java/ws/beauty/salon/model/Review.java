package ws.beauty.salon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    private Integer id;

    // Relación con Client
    @ManyToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client", nullable = false)
    private Client client;

    // Relación con Service
    @ManyToOne
    @JoinColumn(name = "id_service", referencedColumnName = "id_service", nullable = false)
    private Service service;

    @Column(name = "comment")
    private String comment;

    @Column(name = "rating")
    private Integer rating;

    @Column(name = "sentiment", length = 20)
    private String sentiment;

    //key_phrases TEXT,  
    @Column(name = "key_phrases")
    private String key_phrases;
}
