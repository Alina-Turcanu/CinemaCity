package com.example.CinemaCity.Entities;

import jakarta.persistence.*;


@Entity
@Table(name="extraRowPrices")
public class ExtraRowPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int firstRow;  // Rândul de început
    private int lastRow;    // Rândul de sfârșit
    private double price;  // Prețul suplimentar

    // Legătura cu CinemaHall
    @ManyToOne
    @JoinColumn(name = "cinema_hall_id")
    private CinemaHall cinemaHall;

    // Constructor
    public ExtraRowPrice() {}

    public ExtraRowPrice(int startRow, int lastRow, double price, CinemaHall cinemaHall) {
        this.firstRow = startRow;
        this.lastRow = lastRow;
        this.price = price;
        this.cinemaHall = cinemaHall;
    }

    // Getters și setters
    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFirstRow() { return firstRow; }
    public void setFirstRow(int firstRow) { this.firstRow = firstRow; }

    public int getLastRow() { return lastRow; }
    public void setLastRow(int lastRow) { this.lastRow = lastRow; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public CinemaHall getCinemaHall() { return cinemaHall; }
    public void setCinemaHall(CinemaHall cinemaHall) { this.cinemaHall = cinemaHall; }
}

