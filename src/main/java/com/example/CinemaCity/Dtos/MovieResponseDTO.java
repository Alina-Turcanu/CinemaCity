package com.example.CinemaCity.Dtos;

import com.example.CinemaCity.Entities.Genre;

import java.time.LocalDateTime;
import java.util.List;

public class MovieResponseDTO {

    private Long id;

    private String name;

    private List<LocalDateTime> dateAndTimeList;

    private Genre genre;

    private Integer duration;

    private String description;

    private Integer price;
    private Long cinemaHallId;

public MovieResponseDTO(){
}
    public MovieResponseDTO(Long id, String name,List<LocalDateTime> dateAndTimeList, Genre genre,Integer duration, String description, Integer price, Long cinemaHallId) {
        this.id = id;
        this.name = name;
        this.dateAndTimeList = dateAndTimeList;
        this.genre = genre;
        this.duration=duration;
        this.description = description;
        this.price = price;
        this.cinemaHallId = cinemaHallId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LocalDateTime> getDateAndTimeList() {
        return dateAndTimeList;
    }

    public void setDateAndTimeList(List<LocalDateTime> dateAndTimeList) {
        this.dateAndTimeList = dateAndTimeList;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
