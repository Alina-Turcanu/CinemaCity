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

    @PostMapping("/addCinemaHall")
    public ResponseEntity<CinemaHallResponseDTO> createCinemaHall(@RequestBody CinemaHallRequestDTO request) {
        CinemaHallResponseDTO cinemaHall = cinemaHallService.createCinemaHall(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(cinemaHall);
    }

    @GetMapping("/allCinemaHalls")
    public ResponseEntity<List<CinemaHallResponseDTO>> getAllCinemaHalls() {
        List<CinemaHallResponseDTO> cinemaHalls = cinemaHallService.getAllCinemaHalls();
        return ResponseEntity.ok(cinemaHalls);
    }

    @GetMapping("/cinemaHallById/{id}")
    public ResponseEntity<CinemaHallResponseDTO> getCinemaHallById(@PathVariable Long id) {
        CinemaHallResponseDTO cinemaHall = cinemaHallService.getCinemaHallById(id);
        return ResponseEntity.ok(cinemaHall);
    }

    @DeleteMapping("/cinemaHall/{id}")
    public ResponseEntity<String> deleteCinemaHall(@PathVariable Long id) {
        cinemaHallService.deleteCinemaHallById(id);
        return ResponseEntity.ok("Sala de cinema cu id-ul " + id + " a fost stearsa");
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

    @PostMapping("/calculateTicketPrice/{seatId}/{movieId}")
    public ResponseEntity<String> calculateTicketPrice(@PathVariable Long seatId, @PathVariable Long movieId) {
        double ticketPrice = ticketService.calculateTicketPrice(seatId, movieId);
        return ResponseEntity.ok("Pretul biletului este: " + ticketPrice + " lei");
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

    @GetMapping("/viewNumberOfTicketsByMovie/{id}")
    public ResponseEntity<String> viewNumberOfTicketsByMovie(@PathVariable Long id) {
        int numberOfTickets = ticketService.getTheNumberOfTicketsByMovie(id);
        return ResponseEntity.ok("Numarul biletelor asociate filmului cu id-ul " + id + " este: " + numberOfTickets);
    }

    @GetMapping("/viewNumberOfAllTickets")
    public ResponseEntity<String> getNumberOfTicketsFromAllMovies() {
        int numberOfTickets = ticketService.getNumberOfTicketsFromAllMovies();
        return ResponseEntity.ok("Numarul biletelor tuturor filmelor este: " + numberOfTickets);
    }

}
