package com.example.CinemaCity.Entities;

import jakarta.persistence.*;

@Entity
@Table(name="seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_row_number")
    private int seatRowNumber;

    @Column(name = "seat_column_number")
    private int seatColumnNumber;

//    @Enumerated(EnumType.STRING) // Salvează `SeatType` ca text
//    private SeatType seatType;

    private boolean isReserved = false;

    @ManyToOne
    @JoinColumn(name = "cinemaHall_id")
    private CinemaHall cinemaHall;

    private double price;

    public Seat(int seatRowNumber, int seatColumnNumber) {
        this.seatRowNumber = seatRowNumber;
        this.seatColumnNumber = seatColumnNumber;
    }

    public Seat() {
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserveSeat() {
        this.isReserved = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeatRowNumber() {
        return seatRowNumber;
    }

    public void setSeatRowNumber(int seatRowNumber) {
        this.seatRowNumber = seatRowNumber;
    }

    public int getSeatColumnNumber() {
        return seatColumnNumber;
    }

    public void setSeatColumnNumber(int seatColumnNumber) {
        this.seatColumnNumber = seatColumnNumber;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}