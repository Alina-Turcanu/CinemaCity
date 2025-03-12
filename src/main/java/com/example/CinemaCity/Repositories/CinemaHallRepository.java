package com.example.CinemaCity.Repositories;

import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall,Long> {



}
