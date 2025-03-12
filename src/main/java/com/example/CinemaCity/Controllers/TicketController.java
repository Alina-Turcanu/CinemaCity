package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.TicketRequestDTO;
import com.example.CinemaCity.Entities.Ticket;
import com.example.CinemaCity.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/tickets")
public class TicketController {


    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/calculateTicketPrice/{seatId}/{movieId}")
    public ResponseEntity<String> calculateTicketPrice(@PathVariable Long seatId, @PathVariable Long movieId) {
        double ticketPrice = ticketService.calculateTicketPrice(seatId, movieId);
        return ResponseEntity.ok("Pretul biletului este: " + ticketPrice + " lei");
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

