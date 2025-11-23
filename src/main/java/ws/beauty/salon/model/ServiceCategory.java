package ws.beauty.salon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_categories")
public class ServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    @JsonProperty("idCategory")
    private Integer idCategory;

    @Column(name = "category_name", nullable = false)
    @JsonProperty("categoryName")
    private String categoryName;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;
}
