package com.example.CinemaCity.Dtos;

public class ExtraRowPriceRequestDTO {

        private int firstRow;
        private int lastRow;
        private double extraPrice;

    public ExtraRowPriceRequestDTO(int firstRow, int lastRow, double extraPrice) {
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.extraPrice = extraPrice;
    }

    public int getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int firstRow) {
        this.firstRow = firstRow;
    }

    public int getLastRow() {
        return lastRow;
    }

    public void setLastRow(int lastRow) {
        this.lastRow = lastRow;
    }

    public double getExtraPrice() {
        return extraPrice;
    }

    public void setExtraPrice(double extraPrice) {
        this.extraPrice = extraPrice;
    }
}



