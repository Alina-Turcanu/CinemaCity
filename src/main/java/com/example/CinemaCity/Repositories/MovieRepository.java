package com.example.CinemaCity.Repositories;


import com.example.CinemaCity.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    List<Movie> findAllByCinemaHall_Id(Long cinemaHallId);
}
