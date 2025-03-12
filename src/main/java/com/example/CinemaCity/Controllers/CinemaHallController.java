package com.example.CinemaCity.Controllers;


import com.example.CinemaCity.Dtos.CinemaHallRequestDTO;
import com.example.CinemaCity.Dtos.CinemaHallResponseDTO;
import com.example.CinemaCity.Services.CinemaHallService;
import com.example.CinemaCity.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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






}


