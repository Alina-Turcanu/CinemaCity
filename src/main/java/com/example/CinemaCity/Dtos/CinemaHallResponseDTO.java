package com.example.CinemaCity.Dtos;

import com.example.CinemaCity.Entities.ExtraRowPrice;
import com.example.CinemaCity.Entities.Seat;

import java.util.List;

public class CinemaHallResponseDTO {

    private Long id;

    private int numberOfRows;

    private int numberOfColumns;

    private List<ExtraRowPriceResponseDTO> extraRowPrices;

private List<SeatResponseDTO>seats;

    public CinemaHallResponseDTO(){
    }
    public CinemaHallResponseDTO(Long id, int numberOfRows, int numberOfColumns,List<SeatResponseDTO>seats, List<ExtraRowPriceResponseDTO> extraRowPrices) {
        this.id = id;
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
        this.seats=seats;
       // this.basePrice = basePrice;
        this.extraRowPrices = extraRowPrices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public List<SeatResponseDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatResponseDTO> seats) {
        this.seats = seats;
    }

    public List<ExtraRowPriceResponseDTO> getExtraRowPrices() {
        return extraRowPrices;
    }

    public void setExtraRowPrices(List<ExtraRowPriceResponseDTO> extraRowPrices) {
        this.extraRowPrices = extraRowPrices;
    }
}

