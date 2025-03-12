package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.*;
import com.example.CinemaCity.Entities.Seat;
import com.example.CinemaCity.Entities.Ticket;
import com.example.CinemaCity.Services.TicketService;
import com.example.CinemaCity.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    TicketService ticketService;

    UserService userService;


    @Autowired
    public UserController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO user = userService.addUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/buyTicket")
    public ResponseEntity<TicketResponseDTO> buyTicket(@RequestBody TicketRequestDTO ticketRequestDTO) {
        TicketResponseDTO ticket = ticketService.buyTicket(ticketRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/viewFreeSeats/{id}")
    public ResponseEntity<String> viewFreeSeatsByMovie(@PathVariable Long id) {
        List<SeatResponseDTO> seats = userService.viewFreeSeatsByMovie(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Locurile disponibile sunt: " + seats);
    }


    @PutMapping("/{id}")
    public ResponseEntity<String>updateTicket(@PathVariable Long id,@RequestBody TicketRequestDTO ticketRequestDTO){
       TicketResponseDTO ticket= ticketService.changeTicket(id,ticketRequestDTO);
       return ResponseEntity.ok("Biletul a fost updatat "+ ticket);

    }
}