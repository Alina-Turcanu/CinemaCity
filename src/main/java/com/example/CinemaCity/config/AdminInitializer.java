
package com.example.CinemaCity.config;
import com.example.CinemaCity.Entities.Role;
import com.example.CinemaCity.Entities.RoleType;
import com.example.CinemaCity.Entities.User;
import com.example.CinemaCity.Repositories.RoleRepository;
import com.example.CinemaCity.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Verifică dacă rolurile există, altfel le creează
        Role adminRole = roleRepository.findByRoleType(RoleType.ROLE_ADMIN)
                .orElseGet(() -> {
                    Role newAdminRole = new Role();
                    newAdminRole.setRoleType(RoleType.ROLE_ADMIN);
                    return roleRepository.save(newAdminRole);
                });

        Role userRole = roleRepository.findByRoleType(RoleType.ROLE_USER)
                .orElseGet(() -> {
                    Role newUserRole = new Role();
                    newUserRole.setRoleType(RoleType.ROLE_USER);
                    return roleRepository.save(newUserRole);
                });

        // 2. Verifică dacă utilizatorul admin există
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));

            // 3. Adaugă rolurile la utilizator
            admin.setRoles(List.of(adminRole, userRole));

            userRepository.save(admin);
            System.out.println("Admin created: username=admin, password=admin123");
        }
    }
}
