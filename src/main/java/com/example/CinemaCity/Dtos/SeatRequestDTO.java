package com.example.CinemaCity.Dtos;

public class SeatRequestDTO {

    private int numberOfRow;

    private int numberOfColumn;

    public SeatRequestDTO(int numberOfRow, int numberOfColumn) {
        this.numberOfRow = numberOfRow;
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
}
