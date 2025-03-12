package com.example.CinemaCity.Dtos;

import java.util.List;

public class CinemaHallRequestDTO {
    private int numberOfRows;
    private int numberOfColumns;
   // private double basePrice;
    private List<ExtraRowPriceRequestDTO> extraRowPrices;

public CinemaHallRequestDTO(){

}
    public CinemaHallRequestDTO(int numberOfRows, int numberOfColumns, List<ExtraRowPriceRequestDTO> extraRowPrices) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
       // this.basePrice = basePrice;
        this.extraRowPrices = extraRowPrices;
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

//    public double getBasePrice() {
//        return basePrice;
//    }

//    public void setBasePrice(double basePrice) {
//        this.basePrice = basePrice;
//    }

    public List<ExtraRowPriceRequestDTO> getExtraRowPrices() {
        return extraRowPrices;
    }

    public void setExtraRowPrices(List<ExtraRowPriceRequestDTO> extraRowPrices) {
        this.extraRowPrices = extraRowPrices;
    }
}
