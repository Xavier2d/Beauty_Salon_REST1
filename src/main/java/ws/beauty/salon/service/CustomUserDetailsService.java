package ws.beauty.salon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import jakarta.annotation.PostConstruct;
import ws.beauty.salon.model.User;
import ws.beauty.salon.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        // ✅ Agregamos el prefijo ROLE_
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase())
        );
    }

    /*  @PostConstruct
public void testPassword() {
    String raw = "admin123";
    String encoded = "$2a$10$ZlUGOkF2jzy7bt1s3nOfZOSZDU7UfPqFJ7XCKLWx3vSXV9gZqEKh2";

    org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder =
            new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

    System.out.println("✅ Coincide: " + encoder.matches(raw, encoded));
}

@PostConstruct
public void generateHash() {
    org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder encoder =
        new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    System.out.println("Nuevo hash: " + encoder.encode("admin123"));
}*/


}
