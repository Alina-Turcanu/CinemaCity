package com.example.CinemaCity.Repositories;


import com.example.CinemaCity.Entities.Movie;
import com.example.CinemaCity.Entities.Seat;
import com.example.CinemaCity.Entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query("SELECT COUNT(t) > 0 FROM Ticket t WHERE t.seat = :seat AND t.movie = :movie AND t.date = :dateAndTime")
    boolean existsBySeatAndMovieAndDateAndTime(@Param("seat") Seat seat, @Param("movie") Movie movie, @Param("dateAndTime") LocalDateTime dateAndTime);

    @Query("SELECT t FROM Ticket t WHERE FUNCTION('DATE', t.date) = :date")
    List<Ticket> findAllTicketsByDate(@Param("date") LocalDate date);
}
