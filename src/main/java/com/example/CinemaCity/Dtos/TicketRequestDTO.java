package com.example.CinemaCity.Dtos;

import java.time.LocalDateTime;

public class TicketRequestDTO {

    private Long userId;
    private Long movieId;
    private Long seatId;
    private LocalDateTime dateAndTime;


    public TicketRequestDTO(Long userId,Long movieId, Long seatId,LocalDateTime dateAndTime) {
        this.movieId = movieId;
        this.seatId = seatId;
        this.dateAndTime=dateAndTime;
        this.userId=userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}