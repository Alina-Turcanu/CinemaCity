package com.example.CinemaCity.Dtos;

import java.time.LocalDateTime;

public class TicketRequestDTO {

    private Long movieId;
    private Long seatId;
    private Long userId;

    private LocalDateTime dateAndTime;


    public TicketRequestDTO(Long movieId, Long seatId, Long userId,LocalDateTime dateAndTime) {
        this.movieId = movieId;
        this.seatId = seatId;
        this.userId = userId;
        this.dateAndTime=dateAndTime;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}