package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.MovieRequestDTO;
import com.example.CinemaCity.Dtos.MovieResponseDTO;
import com.example.CinemaCity.Services.MovieService;
import com.example.CinemaCity.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/movie")
public class MovieController {

    MovieService movieService;

    TicketService ticketService;

    @Autowired
    public MovieController(MovieService movieService,TicketService ticketService)  {
        this.movieService = movieService;
        this.ticketService=ticketService;
    }


    @PostMapping("/addMovie")
    public ResponseEntity<MovieResponseDTO> addMovie(@RequestBody MovieRequestDTO movieRequestDTO) {
        MovieResponseDTO movie = movieService.addMovie(movieRequestDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movie);
    }

    @GetMapping("/findAllMoviesByCinemaHallId/{cinemaHallId}")
    public ResponseEntity<List<MovieResponseDTO>> findAllMoviesByCinemaHallId(@PathVariable Long cinemaHallId) {
        return ResponseEntity.ok(movieService.findAllMoviesByCinemaHallId(cinemaHallId));
    }

    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) {
        movieService.deleteMovie(movieId);
        return ResponseEntity.ok("Filmul cu id-ul " + movieId + " a fost sters");
    }

    @GetMapping("/viewEarningsOfAMovie")
    public ResponseEntity<String> viewEarningsOfaMovieByDate(@RequestParam Long movieId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
        Long earning = ticketService.viewTheEarningsOfaMovieByDate(movieId, localDate);
        return ResponseEntity.ok("Castigurile din data de " + localDate + " sunt: " + earning + " lei.");
    }

    @GetMapping("/allEarningsByDay")
    public ResponseEntity<String> viewTheEarningsOfADayFromAllMovies(@RequestParam LocalDate date) {
        Long earnings = ticketService.viewTheEarningsForAllMoviesByDate(date);
        return ResponseEntity.ok("Castigurile din data de " + date + " sunt: " + earnings + " lei.");
    }

    }
