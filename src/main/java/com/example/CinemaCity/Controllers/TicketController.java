package com.example.CinemaCity.Controllers;

import com.example.CinemaCity.Dtos.TicketRequestDTO;
import com.example.CinemaCity.Entities.Ticket;
import com.example.CinemaCity.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {


    private TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


}

