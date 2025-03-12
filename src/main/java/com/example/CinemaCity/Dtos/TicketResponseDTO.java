package com.example.CinemaCity.Dtos;

import java.time.LocalDateTime;

public class TicketResponseDTO {


    private Long id;

    private Long seatId;

    private Long movieId;

    private LocalDateTime purchaseDate;

    private LocalDateTime dateAndTime;

    private Long cinemaHallId;

    private double price;
    public TicketResponseDTO() {

    }

    public TicketResponseDTO(Long id, Long seatId, Long movieId, LocalDateTime purchaseDate, LocalDateTime dateAndTime, Long cinemaHallId,double price) {
        this.id = id;
        this.seatId = seatId;
        this.movieId = movieId;
        this.purchaseDate = purchaseDate;
        this.dateAndTime = dateAndTime;
        this.cinemaHallId = cinemaHallId;
        this.price=price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
