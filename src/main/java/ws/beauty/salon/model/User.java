package ws.beauty.salon.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer id;

    @Column(name = "userName", unique = true, nullable = false, length = 50)
    private String username;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @OneToOne
    @JoinColumn(name = "id_client", referencedColumnName = "id_client")
    private Client client;

    @OneToOne
    @JoinColumn(name = "id_stylist", referencedColumnName = "id_stylist")
    private Stylist stylist;

    // ============ IMPLEMENTACIÓN DE UserDetails ============
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir el role en una autoridad de Spring Security
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @PrePersist
    @PreUpdate
    private void validateExclusiveRelations() {
        if (client != null && stylist != null) {
            throw new IllegalStateException("A user cannot be both a client and a stylist at the same time.");
        }
        if (client == null && stylist == null) {
            throw new IllegalStateException("A stylist must be assigned to the stylist role.");
        }
    }
}