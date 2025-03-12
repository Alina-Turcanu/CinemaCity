package com.example.CinemaCity.Services;

import com.example.CinemaCity.Dtos.SeatResponseDTO;
import com.example.CinemaCity.Dtos.TicketRequestDTO;
import com.example.CinemaCity.Dtos.TicketResponseDTO;
import com.example.CinemaCity.Entities.*;
import com.example.CinemaCity.Exceptions.ResourceNotFoundException;
import com.example.CinemaCity.Exceptions.SeatAlreadyTakenException;
import com.example.CinemaCity.Repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;

    private MovieRepository movieRepository;

    private UserRepository userRepository;

    private SeatRepository seatRepository;

    private CinemaHallRepository cinemaHallRepository;

    private EmailService emailService;


    @Autowired
    public TicketService(TicketRepository ticketRepository, MovieRepository movieRepository, UserRepository userRepository, SeatRepository seatRepository, CinemaHallRepository cinemaHallRepository,EmailService emailService) {
        this.ticketRepository = ticketRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.seatRepository = seatRepository;
        this.cinemaHallRepository = cinemaHallRepository;
        this.emailService= emailService;
    }

    public double calculateTicketPrice(Long seatId, Long movieId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ResourceNotFoundException("Locul nu a fost găsit"));

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResourceNotFoundException("Filmul nu a fost găsit"));

        double basePrice = movie.getPrice();
        int seatRow = seat.getSeatRowNumber();
        CinemaHall cinemaHall = seat.getCinemaHall();

        for (ExtraRowPrice extraPrice : cinemaHall.getExtraRowPrices()) {
            if (seatRow >= extraPrice.getFirstRow() && seatRow <= extraPrice.getLastRow()) {
                return basePrice + extraPrice.getPrice();
            }
        }
        return basePrice;
    }

    @Transactional
    public TicketResponseDTO buyTicket(TicketRequestDTO ticketRequestDTO) {
        Seat seat = seatRepository.findById(ticketRequestDTO.getSeatId())
                .orElseThrow(() -> new ResourceNotFoundException("Locul nu a fost găsit"));
        Movie movie = movieRepository.findById(ticketRequestDTO.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException("Filmul nu a fost găsit"));
        User user = userRepository.findById(ticketRequestDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User-ul nu a fost găsit"));
        LocalDateTime requestedDateTime = ticketRequestDTO.getDateAndTime();

        boolean isSeatTaken = ticketRepository.existsBySeatAndMovieAndDateAndTime(seat, movie, requestedDateTime);
        if (isSeatTaken) {
            throw new SeatAlreadyTakenException("Acest loc este deja rezervat pentru filmul selectat la data și ora specificată.");
        }

        double price = calculateTicketPrice(seat.getId(), movie.getId());

        Ticket ticket = new Ticket();
        ticket.setMovie(movie);
        ticket.setSeat(seat);
        ticket.setUser(user);
        ticket.setPrice(price);
        ticket.setDate(requestedDateTime);
        ticket.setPurchaseDate(LocalDateTime.now());
        ticket.setSold(true);
        ticket.setCinemaHall(movie.getCinemaHall());

        ticketRepository.save(ticket);
        seat.setReserved(true);
        seatRepository.save(seat);
        sendPurchaseConfirmationAsync(user.getEmail(), movie.getName(), requestedDateTime, seat, price);

        return mapFromTicketToTicketResponseDTO(ticket);
    }

    @Async
    public void sendPurchaseConfirmationAsync(String email, String movieName, LocalDateTime date, Seat seat, double price) {
        String subject = "Confirmare cumpărare bilet";
        String content = "<h2>Felicitări!</h2>" +
                "<p>Ai cumpărat un bilet la filmul <strong>" + movieName + "</strong>.</p>" +
                "<p><strong>Data:</strong> " + date + "</p>" +
                "<p><strong>Loc:</strong> Rând " + seat.getSeatRowNumber() + ", Coloană " + seat.getSeatColumnNumber() + "</p>" +
                "<p><strong>Preț:</strong> " + price + " RON</p>" +
                "<p>Îți mulțumim pentru achiziție și te așteptăm la film! 🎬</p>";

        try {
            emailService.sendEmail(email, subject, content);
            System.out.println("E-mail de confirmare trimis către " + email);
        } catch (Exception e) {
            System.err.println("Eroare la trimiterea e-mailului: " + e.getMessage());
        }
    }


    public SeatResponseDTO mapFromSeatToSeatResponseDTO(Seat seat) {
        SeatResponseDTO seatResponseDTO = new SeatResponseDTO();
        seatResponseDTO.setNumberOfRow(seat.getSeatRowNumber());
        seatResponseDTO.setNumberOfColumn(seat.getSeatColumnNumber());
        return seatResponseDTO;
    }

    @Transactional
    public TicketResponseDTO mapFromTicketToTicketResponseDTO(Ticket ticket) {
        TicketResponseDTO ticketResponseDTO = new TicketResponseDTO();
        ticketResponseDTO.setId(ticket.getId());
        ticketResponseDTO.setMovieId(ticket.getMovie().getId());
        Seat seat = ticket.getSeat();
        ticketResponseDTO.setSeatResponseDTO(mapFromSeatToSeatResponseDTO(seat));
        ticketResponseDTO.setPurchaseDate(LocalDateTime.now());
        ticketResponseDTO.setDateAndTime(ticket.getDate());
        ticketResponseDTO.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketResponseDTO.setPrice(ticket.getPrice());
        return ticketResponseDTO;
    }

    @Transactional
    public Long viewTheEarningsOfaMovieByDate(Long id, LocalDate date) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filmul nu a fost gasit"));
        List<Ticket> allTicketsByDay = ticketRepository.findAllTicketsByDate(date);
        long earnings = allTicketsByDay.stream()
                .filter(ticket -> ticket.getMovie().getId().equals(id))
                .mapToLong(ticket -> movie.getPrice())
                .sum();
        return earnings;
    }

    @Transactional
    public Long viewTheEarningsForAllMoviesByDate(LocalDate date) {
        List<Ticket> allTicketsByDay = ticketRepository.findAllTicketsByDate(date);

        long totalEarnings = allTicketsByDay.stream()
                .mapToLong(ticket -> {
                    long ticketPrice = ticket.getMovie().getPrice();
                    return ticketPrice;
                })
                .sum();

        return totalEarnings;
    }

    @Transactional
    public int getTheNumberOfTicketsByMovie(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        return movie.getTickets() != null ? movie.getTickets().size() : 0;

    }

    @Transactional
    public int getNumberOfTicketsFromAllMovies() {
        List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
        return cinemaHalls.stream()
                .flatMap(cinemaHall -> cinemaHall.getMovies().stream())
                .flatMap(movie -> movie.getTickets().stream())
                .mapToInt(ticket -> 1)
                .sum();
    }

    @Transactional
    public TicketResponseDTO changeTicket(Long id, TicketRequestDTO ticketRequestDTO) {
        Ticket ticket = ticketRepository.findById(id).orElseThrow(() -> new RuntimeException("Biletul nu a fost gasit"));
        Seat seat = seatRepository.findById(ticketRequestDTO.getSeatId()).orElseThrow(() -> new RuntimeException("Scaunul nu a fost gasit"));
        Movie movie = movieRepository.findById(ticketRequestDTO.getMovieId()).orElseThrow(() -> new RuntimeException("Filmul nu afost gasit"));
        String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        User user = userRepository.findByUsername(usernameLoggedIn)
                .orElseThrow(() -> new ResourceNotFoundException("User-ul nu a fost găsit"));
        LocalDateTime dateAndTime = ticketRequestDTO.getDateAndTime();
        ticket.setSeat(seat);
        ticket.setMovie(movie);
        ticket.setUser(user);
        ticket.setDate(dateAndTime);
        Ticket newTicket = ticketRepository.save(ticket);

        return mapFromTicketToTicketResponseDTO(newTicket);
    }
}