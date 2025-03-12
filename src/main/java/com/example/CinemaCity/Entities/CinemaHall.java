package com.example.CinemaCity.Entities;

import com.example.CinemaCity.Dtos.CinemaHallRequestDTO;
import com.example.CinemaCity.Repositories.SeatRepository;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "cinemaHalls")
public class CinemaHall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int numberOfRows;
    @Column
    private int numberOfColumns;

    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaHall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExtraRowPrice> extraRowPrices = new ArrayList<>();

    @OneToMany(mappedBy = "cinemaHall",cascade =CascadeType.ALL,orphanRemoval = true)
    private List<Movie> movies;

    @OneToMany(mappedBy = "cinemaHall")
    private List<Ticket> tickets;

    public CinemaHall() {
    }

    public CinemaHall(int numberOfRows, int numberOfColumns) {
        this.numberOfRows = numberOfRows;
        this.numberOfColumns = numberOfColumns;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public List<ExtraRowPrice> getExtraRowPrices() {
        return extraRowPrices;
    }

    public void setExtraRowPrices(List<ExtraRowPrice> extraRowPrices) {
        this.extraRowPrices = extraRowPrices;
    }


    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addExtraRowPrice(ExtraRowPrice extraRowPrice) {
        this.extraRowPrices.add(extraRowPrice);
        extraRowPrice.setCinemaHall(this);
    }



}