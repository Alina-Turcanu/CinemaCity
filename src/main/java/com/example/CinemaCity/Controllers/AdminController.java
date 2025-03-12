package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.CinemaHallRequestDTO;
import com.example.CinemaCity.Dtos.CinemaHallResponseDTO;
import com.example.CinemaCity.Dtos.MovieRequestDTO;
import com.example.CinemaCity.Dtos.MovieResponseDTO;
import com.example.CinemaCity.Services.CinemaHallService;
import com.example.CinemaCity.Services.MovieService;
import com.example.CinemaCity.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    CinemaHallService cinemaHallService;

    MovieService movieService;

    TicketService ticketService;

    @Autowired
    public AdminController(CinemaHallService cinemaHallService, MovieService movieService, TicketService ticketService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
        this.ticketService = ticketService;
    }













}
