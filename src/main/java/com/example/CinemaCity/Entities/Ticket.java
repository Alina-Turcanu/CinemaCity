package com.example.CinemaCity.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double price; // Folosește un tip de date corect pentru preț, de obicei Double sau BigDecimal

    @ManyToOne
    @JoinColumn(name = "Movie_id")
    private Movie movie;


    @ManyToOne
    @JoinColumn(name = "seat_id") // Aici legăm Ticket de Seat
    private Seat seat;

    @ManyToOne
    @JoinColumn(name = "cinema_hall_id") // Corectăm numele coloanei pentru CinemaHall
    private CinemaHall cinemaHall;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name="date_And_Time")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;


    @Column(nullable = false)
    private boolean isSold;

    @Column(name="purchased_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime purchaseDate;

    public Ticket() {
    }

    public Ticket(Seat seat, Double price, CinemaHall cinemaHall, User user, boolean isSold, LocalDateTime date, LocalDateTime purchaseDate) {
        this.seat = seat;
        this.price = price;
        this.cinemaHall = cinemaHall;
        this.user = user;
        this.isSold = isSold;
        this.date = date;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
}
