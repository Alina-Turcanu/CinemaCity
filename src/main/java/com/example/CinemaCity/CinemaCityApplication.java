package com.example.CinemaCity;

import com.example.CinemaCity.Entities.Role;
import com.example.CinemaCity.Entities.RoleType;
import com.example.CinemaCity.Entities.Ticket;
import com.example.CinemaCity.Repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CinemaCityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaCityApplication.class, args);
	}

	@Bean
	CommandLineRunner initRoles(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByRoleType(RoleType.ROLE_USER) == null) {
				Role userRole = new Role();
				userRole.setRoleType(RoleType.ROLE_USER);
				roleRepository.save(userRole);
			}

		};
	}
}
