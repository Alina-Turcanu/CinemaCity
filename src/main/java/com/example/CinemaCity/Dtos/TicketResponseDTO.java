package com.example.CinemaCity.Dtos;

import com.example.CinemaCity.Entities.Seat;

import java.time.LocalDateTime;

public class TicketResponseDTO {


    private Long id;

    //private Long seatId;
    private SeatResponseDTO seat;
    private Long movieId;

    private LocalDateTime purchaseDate;

    private LocalDateTime dateAndTime;

    private Long cinemaHallId;

    private double price;

    public TicketResponseDTO() {

    }

    public TicketResponseDTO(Long id,SeatResponseDTO seat, Long movieId, LocalDateTime purchaseDate, LocalDateTime dateAndTime, Long cinemaHallId, double price) {
        this.id = id;
        this.seat=seat;
        this.movieId = movieId;
        this.purchaseDate = purchaseDate;
        this.dateAndTime = dateAndTime;
        this.cinemaHallId = cinemaHallId;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SeatResponseDTO getSeat() {
        return seat;
    }

    public void setSeatResponseDTO(SeatResponseDTO seat) {
        this.seat = seat;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seat=" + seat +
                ", movieId=" + movieId +
                ", purchaseDate=" + purchaseDate +
                ", dateAndTime=" + dateAndTime +
                ", cinemaHallId=" + cinemaHallId +
                ", price=" + price +
                '}';
    }
}
