package com.example.CinemaCity.Dtos;

public class SeatResponseDTO {

    private Long id;
    private int numberOfRow;         // Rândul scaunului
    private int numberOfColumn;      // Coloana scaunului

    private Long cinemaHallId;
    public SeatResponseDTO() {

    }

    public SeatResponseDTO(Long id,int numberofRow, int numberOfColumn,Long cinemaHallId) {
        this.id=id;
        this.numberOfRow = numberofRow;
        this.numberOfColumn = numberOfColumn;
        this.cinemaHallId=cinemaHallId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    @Override
    public String toString() {
        return "SeatResponseDTO{" +
                "id=" + id +
                ", numberOfRow=" + numberOfRow +
                ", numberOfColumn=" + numberOfColumn +
                ", cinemaHallId=" + cinemaHallId +
                '}';
    }
}