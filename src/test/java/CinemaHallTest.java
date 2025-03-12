
import com.example.CinemaCity.Entities.CinemaHall;
import com.example.CinemaCity.Entities.ExtraRowPrice;
import com.example.CinemaCity.Entities.Seat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

//public class CinemaHallTest {
//
//    private CinemaHall cinemaHall;
//    private ExtraRowPrice extraRowPrice1;
//    private ExtraRowPrice extraRowPrice2;
//
//    @BeforeEach
//    public void setUp() {
//        // Creăm o sală de cinema cu 3 rânduri și 3 coloane și preț de bază 10
//        cinemaHall = new CinemaHall(3, 3, 10.0);
//
//        // Adăugăm două prețuri extra
//        extraRowPrice1 = new ExtraRowPrice(1, 1, 5.0, cinemaHall);  // Preț extra pentru rândul 1
//        extraRowPrice2 = new ExtraRowPrice(2, 2, 3.0, cinemaHall);  // Preț extra pentru rândul 2
//
//        cinemaHall.addExtraRowPrice(extraRowPrice1);
//        cinemaHall.addExtraRowPrice(extraRowPrice2);
//    }

//    @Test
//    public void testGenerateSeats() {
//        // Apelăm metoda generateSeats
//        cinemaHall.generateSeats();
//
//        // Verificăm dacă numărul de locuri generate este corect (3 rânduri * 3 coloane)
//        List<Seat> seats = cinemaHall.getSeats();
//        assertEquals(9, seats.size(), "Numărul de locuri ar trebui să fie 9.");
//
//        // Verificăm prețurile locurilor
//        // Rândul 1 ar trebui să aibă un preț de bază + 5 (preț extra pentru rândul 1)
//        Seat seat1 = seats.get(0); // Locul din rândul 1, coloana 1
//        assertEquals(15.0, seat1.getPrice(), "Prețul locului din rândul 1 ar trebui să fie 15.0.");
//
//        // Rândul 2 ar trebui să aibă un preț de bază + 3 (preț extra pentru rândul 2)
//        Seat seat4 = seats.get(3); // Locul din rândul 2, coloana 1
//        assertEquals(13.0, seat4.getPrice(), "Prețul locului din rândul 2 ar trebui să fie 13.0.");
//
//        // Rândul 3 ar trebui să aibă doar prețul de bază (fără preț extra)
//        Seat seat7 = seats.get(6); // Locul din rândul 3, coloana 1
//        assertEquals(10.0, seat7.getPrice(), "Prețul locului din rândul 3 ar trebui să fie 10.0.");
//    }
//}
