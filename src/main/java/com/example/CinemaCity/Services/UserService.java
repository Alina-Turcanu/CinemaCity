package com.example.CinemaCity.Services;


import com.example.CinemaCity.Dtos.SeatResponseDTO;
import com.example.CinemaCity.Dtos.UserRequestDTO;
import com.example.CinemaCity.Dtos.UserResponseDTO;
import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.Movie;
import com.example.CinemaCity.Entities.Seat;
import com.example.CinemaCity.Entities.User;
import com.example.CinemaCity.Exceptions.ResourceNotFoundException;
import com.example.CinemaCity.Repositories.MovieRepository;
import com.example.CinemaCity.Repositories.TicketRepository;
import com.example.CinemaCity.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    UserRepository userRepository;
    MovieRepository movieRepository;
    TicketRepository ticketRepository;


    @Autowired
    public UserService(UserRepository userRepository, MovieRepository movieRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) {
        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        User savedUser = userRepository.save(user);
        return mapFromUserToUserResponseDTO(savedUser);

    }

    @Transactional
    public UserResponseDTO mapFromUserToUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        return userResponseDTO;
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
                .map(seat -> new SeatResponseDTO(seat.getId(), seat.getSeatRowNumber(), seat.getSeatColumnNumber(), seat.getCinemaHall().getId()))
                .collect(Collectors.toList());
    }
}
