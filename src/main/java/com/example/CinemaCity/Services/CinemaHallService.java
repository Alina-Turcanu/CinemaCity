package com.example.CinemaCity.Services;

import com.example.CinemaCity.Dtos.*;
import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.ExtraRowPrice;
import com.example.CinemaCity.Entities.Seat;
import com.example.CinemaCity.Exceptions.ResourceNotFoundException;
import com.example.CinemaCity.Repositories.CinemaHallRepository;
import com.example.CinemaCity.Repositories.SeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CinemaHallService {


    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    public CinemaHallService(CinemaHallRepository cinemaHallRepository) {
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Transactional
    public CinemaHallResponseDTO createCinemaHall(CinemaHallRequestDTO cinemaHallRequestDTO) {

        CinemaHall cinemaHall = new CinemaHall(cinemaHallRequestDTO.getNumberOfRows(), cinemaHallRequestDTO.getNumberOfColumns());
        List<Seat> seats = generateSeats(cinemaHallRequestDTO,cinemaHall);
        cinemaHall.setSeats(seats);
        if (cinemaHallRequestDTO.getExtraRowPrices() != null) {
            for (ExtraRowPriceRequestDTO extraRowPriceDTO : cinemaHallRequestDTO.getExtraRowPrices()) {
                ExtraRowPrice extraRowPrice = new ExtraRowPrice(
                        extraRowPriceDTO.getFirstRow(),
                        extraRowPriceDTO.getLastRow(),
                        extraRowPriceDTO.getExtraPrice(),
                        cinemaHall
                );
                cinemaHall.addExtraRowPrice(extraRowPrice);
            }
        }

        CinemaHall savedCinemaHall = cinemaHallRepository.save(cinemaHall);

        return mapFromCinemaHallToCinemaHallResponseDTO(savedCinemaHall);
    }


    public List<Seat> generateSeats(CinemaHallRequestDTO cinemaHallRequestDTO,CinemaHall cinemaHall) {
        List<Seat> seats = new ArrayList<>();

        for (int row = 1; row <= cinemaHallRequestDTO.getNumberOfRows(); row++) {
            for (int col = 1; col <= cinemaHallRequestDTO.getNumberOfColumns(); col++) {
                Seat seat = new Seat(row,col);
                seat.setCinemaHall(cinemaHall); // Asignează corect referința
                seats.add(seat);
            }
        }
        return seats;
    }
@Transactional
     public List<SeatResponseDTO> mapFromSeatToSeatResponseDTO(CinemaHall cinemaHall){
        List<Seat>seats=cinemaHall.getSeats();
        return seats.stream()
                .map(seat->{
                SeatResponseDTO seatResponseDTO=new SeatResponseDTO();
                seatResponseDTO.setNumberOfRow(seat.getSeatRowNumber());
                seatResponseDTO.setNumberOfColumn(seat.getSeatColumnNumber());
                seatResponseDTO.setCinemaHallId(cinemaHall.getId());
                return seatResponseDTO;
                }).collect(Collectors.toList());
}

    @Transactional
    public List<ExtraRowPriceResponseDTO> mapFromExtraRowPriceToExtraRowPriceResponseDTO(CinemaHall cinemaHall) {
        List<ExtraRowPrice> extraRowPrices = cinemaHall.getExtraRowPrices();

        return extraRowPrices.stream()
                .map(price -> {
                    ExtraRowPriceResponseDTO extraRowPriceResponseDTO = new ExtraRowPriceResponseDTO();
                    extraRowPriceResponseDTO.setFirstRow(price.getFirstRow());
                    extraRowPriceResponseDTO.setLastRow(price.getLastRow());

                    double finalPrice = price.getPrice();
                    extraRowPriceResponseDTO.setExtraPrice(finalPrice);

                    return extraRowPriceResponseDTO;
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public CinemaHallResponseDTO mapFromCinemaHallToCinemaHallResponseDTO(CinemaHall cinemaHall) {
        CinemaHallResponseDTO cinemaHallResponseDTO = new CinemaHallResponseDTO();
        cinemaHallResponseDTO.setId(cinemaHall.getId());
        cinemaHallResponseDTO.setNumberOfRows(cinemaHall.getNumberOfRows());
        cinemaHallResponseDTO.setNumberOfColumns(cinemaHall.getNumberOfColumns());
        List<SeatResponseDTO>seats=mapFromSeatToSeatResponseDTO(cinemaHall);
        cinemaHallResponseDTO.setSeats(seats);
        List<ExtraRowPriceResponseDTO> extraRowPrices = mapFromExtraRowPriceToExtraRowPriceResponseDTO(cinemaHall);
        cinemaHallResponseDTO.setExtraRowPrices(extraRowPrices);
        return cinemaHallResponseDTO;
    }

    @Transactional
    public void deleteCinemaHallById(Long id) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sala de cinema nu a fost găsită"));

        cinemaHallRepository.delete(cinemaHall);
    }

    @Transactional
    public CinemaHallResponseDTO getCinemaHallById(Long id) {
        List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
        return cinemaHalls.stream()
                .filter(cinemaHall -> cinemaHall.getId().equals(id)) // Filtrare după ID
                .findFirst()
                .map(cinemaHall -> mapFromCinemaHallToCinemaHallResponseDTO(cinemaHall))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cinema Hall not found"));
    }

    @Transactional
    public List<CinemaHallResponseDTO> getAllCinemaHalls() {
        List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
        return cinemaHalls.stream().map(cinemaHall -> mapFromCinemaHallToCinemaHallResponseDTO(cinemaHall))
                .collect(Collectors.toList());
    }
}