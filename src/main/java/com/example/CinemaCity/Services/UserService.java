package com.example.CinemaCity.Services;


import com.example.CinemaCity.Dtos.*;
import com.example.CinemaCity.Entities.*;
import com.example.CinemaCity.Exceptions.ResourceNotFoundException;
import com.example.CinemaCity.Repositories.MovieRepository;
import com.example.CinemaCity.Repositories.RoleRepository;
import com.example.CinemaCity.Repositories.TicketRepository;
import com.example.CinemaCity.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;
    MovieRepository movieRepository;
    TicketRepository ticketRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private AuthenticationManager authenticationManager;

    private UserDetailsServiceImpl userDetailsService;

    private JwtTokenService jwtTokenService;

    @Autowired
    public UserService(UserRepository userRepository, MovieRepository movieRepository, TicketRepository ticketRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenService = jwtTokenService;
    }

    @Transactional
    public AuthResponseDTO register(AuthRequestDTO authRequestDTO) {
        User user = new User();
        user.setUsername(authRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authRequestDTO.getPassword()));

        // Caută rolul ROLE_USER în baza de date
        Role role = roleRepository.findByRoleType(RoleType.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role USER not found"));

        // Adaugă rolul utilizatorului
        user.getRoles().add(role);

        // Adaugă utilizatorul în lista rolului (opțional)
        role.getUsers().add(user);
        user.setEmail(authRequestDTO.getEmail());
        User savedUser = userRepository.save(user);
        return mapFromUserToAuthResponseDTO(savedUser);
    }

    @Transactional
    public AuthResponseDTO mapFromUserToAuthResponseDTO(User user) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setId(user.getId());
        authResponseDTO.setUsername(user.getUsername());
        authResponseDTO.setPassword(user.getPassword());
        authResponseDTO.setEmail(user.getEmail());
        return authResponseDTO;
    }


    public String authenticate(AuthRequestDTO authRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequestDTO.getUsername());
        String token = jwtTokenService.generateToken(userDetails);
        return token;
    }

    public User findLoggedInUser() {
        String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return userRepository.findByUsername(usernameLoggedIn).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }



    @Transactional
    public List<SeatResponseDTO> viewFreeSeatsByMovie(Long id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filmul nu a fost gasit"));

        CinemaHall hall = movie.getCinemaHall();
        List<Seat> allSeats = hall.getSeats();

        List<Seat> occupiedSeats = movie.getTickets().stream()
                .map(ticket -> ticket.getSeat())
                .collect(Collectors.toList());

        return allSeats.stream()
                .filter(seat -> !occupiedSeats.contains(seat))
                .map(seat -> new SeatResponseDTO(seat.getSeatRowNumber(), seat.getSeatColumnNumber()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}