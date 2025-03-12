package com.example.CinemaCity.Services;

import com.example.CinemaCity.Dtos.MovieRequestDTO;
import com.example.CinemaCity.Dtos.MovieResponseDTO;
import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.Movie;
import com.example.CinemaCity.Exceptions.ResourceNotFoundException;
import com.example.CinemaCity.Repositories.CinemaHallRepository;
import com.example.CinemaCity.Repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MovieService {

    private MovieRepository movieRepository;

    private CinemaHallRepository cinemaHallRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, CinemaHallRepository cinemaHallRepository) {
        this.movieRepository = movieRepository;
        this.cinemaHallRepository = cinemaHallRepository;
    }

    @Transactional
    public MovieResponseDTO addMovie(MovieRequestDTO movieRequestDTO) {
        CinemaHall cinemaHall = cinemaHallRepository.findById(movieRequestDTO.getCinemaHallId())
                .orElseThrow(() -> new RuntimeException("Cinema Hall not found"));
        Movie movie = new Movie(movieRequestDTO.getName(), movieRequestDTO.getDateAndTimeList(), movieRequestDTO.getGenre(), movieRequestDTO.getDuration(), movieRequestDTO.getDescription(), movieRequestDTO.getPrice(), cinemaHall);
        Movie savedMovie = movieRepository.save(movie);
        return mapFromMovieToMovieResponseDTO(savedMovie);
    }

    @Transactional
    public MovieResponseDTO mapFromMovieToMovieResponseDTO(Movie movie) {
        MovieResponseDTO movieResponseDTO = new MovieResponseDTO();
        movieResponseDTO.setId(movie.getId());
        movieResponseDTO.setName(movie.getName());
        movieResponseDTO.setGenre(movie.getGenre());
        movieResponseDTO.setDuration(movie.getDuration());
        movieResponseDTO.setDescription(movie.getDescription());
        movieResponseDTO.setPrice(movie.getPrice());
        movieResponseDTO.setDateAndTimeList(movie.getDateAndTimeList());
        movieResponseDTO.setCinemaHallId(movie.getCinemaHall().getId());
        return movieResponseDTO;
    }


    public void deleteMovie(Long movieId){
        movieRepository.findById(movieId).orElseThrow(()->new ResourceNotFoundException("Filmul cu id-ul "+ movieId + " nu a fost gasit"));
        movieRepository.deleteById(movieId);
    }
    public List<MovieResponseDTO> findAllMoviesByCinemaHallId(Long cinemaHallId){
        return movieRepository.findAllByCinemaHall_Id(cinemaHallId).stream()
                .map(movie->mapFromMovieToMovieResponseDTO(movie))
                .collect(Collectors.toList());

    }
}
