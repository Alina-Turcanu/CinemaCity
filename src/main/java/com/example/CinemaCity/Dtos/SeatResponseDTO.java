package com.example.CinemaCity.Dtos;

public class SeatResponseDTO {

    private int numberOfRow;         // Rândul scaunului
    private int numberOfColumn;      // Coloana scaunului

    private Long cinemaHallId;

    public SeatResponseDTO() {

    }

    public SeatResponseDTO(int numberofRow, int numberOfColumn) {
        this.numberOfRow = numberofRow;
        this.numberOfColumn = numberOfColumn;

    }

    public int getNumberOfRow() {
        return numberOfRow;
    }

    public void setNumberOfRow(int numberOfRow) {
        this.numberOfRow = numberOfRow;
    }

    public int getNumberOfColumn() {
        return numberOfColumn;
    }

    public void setNumberOfColumn(int numberOfColumn) {
        this.numberOfColumn = numberOfColumn;
    }


    @Override
    public String toString() {
        return "Seat{" +
                ", numberOfRow=" + numberOfRow +
                ", numberOfColumn=" + numberOfColumn +
                '}';
    }
}