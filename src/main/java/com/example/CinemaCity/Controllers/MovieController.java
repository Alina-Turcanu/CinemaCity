package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.MovieRequestDTO;
import com.example.CinemaCity.Dtos.MovieResponseDTO;
import com.example.CinemaCity.Entities.Movie;
import com.example.CinemaCity.Services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/movie")
public class MovieController {

    MovieService movieService;

    public MovieController(MovieService movieService)  {
        this.movieService = movieService;
    }

    }
