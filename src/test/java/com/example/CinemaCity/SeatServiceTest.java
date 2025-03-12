//package com.example.CinemaCity;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import com.example.CinemaCity.Entities.CinemaHall;
//import com.example.CinemaCity.Entities.ExtraRowPrice;
//import com.example.CinemaCity.Entities.Seat;
//import com.example.CinemaCity.Repositories.CinemaHallRepository;
//import com.example.CinemaCity.Repositories.SeatRepository;
//import com.example.CinemaCity.Services.SeatService;
//
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Arrays;
//
//public class SeatServiceTest {
//
//    @Mock
//    private SeatRepository seatRepository;  // Mock pentru SeatRepository
//
//    @Mock
//    private CinemaHallRepository cinemaHallRepository; // Mock pentru CinemaHallRepository
//
//    @InjectMocks
//    private SeatService seatService;  // Instanță a SeatService (clasa care conține metoda generateSeats)
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this); // Inițializează Mocks
//    }
//
//    @Test
//    public void testGenerateSeats() {
//        // Date de intrare pentru test
//        int numberOfRows = 5;
//        int numberOfColumns = 5;
//        double basePrice = 10.0;
//
//        // Creăm un exemplu de listă cu prețuri extra pentru rânduri
//        ExtraRowPrice extraRowPrice1 = new ExtraRowPrice(1, 2, 2.0, null);  // Preț extra pentru rândul 1 și 2
//        ExtraRowPrice extraRowPrice2 = new ExtraRowPrice(3, 4, 3.0, null);  // Preț extra pentru rândul 3 și 4
//        List<ExtraRowPrice> extraRowPrices = Arrays.asList(extraRowPrice1, extraRowPrice2);
//
//        CinemaHall cinemaHall = new CinemaHall(numberOfRows, numberOfColumns, basePrice);
//
//        // Apelează metoda generateSeats
//       // seatService.generateSeats(numberOfRows, numberOfColumns, basePrice, extraRowPrices, cinemaHall);
//
//        // Verifică dacă metoda saveAll a fost apelată pe SeatRepository
//        verify(seatRepository, times(1)).saveAll(anyList());  // Verifică dacă scaunele au fost salvate
//
//        // Verifică dacă numărul de scaune generate este corect
//        assertEquals(numberOfRows * numberOfColumns, cinemaHall.getSeats().size());
//
//        // Verifică dacă prețul scaunelor pentru rândurile cu preț extra este corect
//        Seat seat = cinemaHall.getSeats().get(0); // Verificăm primul scaun
//        assertEquals(basePrice + 2.0, seat.getPrice()); // Prețul pentru primul scaun ar trebui să fie basePrice + extraPrice
//
//        Seat seat2 = cinemaHall.getSeats().get(10); // Verificăm un scaun de pe rândul 3-4
//        assertEquals(basePrice + 3.0, seat2.getPrice()); // Prețul pentru un scaun de pe rândul 3-4
//    }
//}
