package com.example.CinemaCity.Controllers;


import com.example.CinemaCity.Dtos.CinemaHallResponseDTO;
import com.example.CinemaCity.Dtos.MovieResponseDTO;
import com.example.CinemaCity.Services.CinemaHallService;
import com.example.CinemaCity.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {

    private CinemaHallService cinemaHallService;
    private MovieService movieService;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService, MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }








}


