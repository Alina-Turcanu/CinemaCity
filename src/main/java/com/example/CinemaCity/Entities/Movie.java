package com.example.CinemaCity.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    //    @Column
//    private LocalDateTime dateAndTime;
    @ElementCollection
    private List<LocalDateTime> dateAndTimeList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Genre genre;

    private Integer duration;
    @Column
    private String description;

    @Column
    private Integer price;
    @ManyToOne
    @JoinColumn(name = "cinemaHall_id")
    private CinemaHall cinemaHall;

    @OneToMany(mappedBy = "movie",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket>tickets;

    public Movie() {

    }

    public Movie(String name, List<LocalDateTime> dateAndTimeList, Genre genre, Integer duration, String description, Integer price, CinemaHall cinemaHall) {
        this.name = name;
        this.dateAndTimeList = dateAndTimeList;
        this.genre = genre;
        this.duration = duration;
        this.description = description;
        this.price = price;
        this.cinemaHall = cinemaHall;
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

    public CinemaHall getCinemaHall() {
        return cinemaHall;
    }

    public void setCinemaHall(CinemaHall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
