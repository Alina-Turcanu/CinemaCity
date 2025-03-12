package com.example.CinemaCity.Services;

import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.ExtraRowPrice;
import com.example.CinemaCity.Entities.Seat;
import com.example.CinemaCity.Repositories.CinemaHallRepository;
import com.example.CinemaCity.Repositories.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    private CinemaHallRepository cinemaHallRepository;


    @Autowired
    public SeatService(SeatRepository seatRepository, CinemaHallRepository cinemaHallRepository) {
        this.seatRepository = seatRepository;
        this.cinemaHallRepository = cinemaHallRepository;
    }
}