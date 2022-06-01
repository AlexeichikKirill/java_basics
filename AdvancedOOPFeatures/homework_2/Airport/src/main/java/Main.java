import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date now = new Date();
        Date afterTwoHours = new Date();
        afterTwoHours.setHours(afterTwoHours.getHours() + 2);

        List<Flight> planesLeavingInTheNextTwoHours = new ArrayList<>();

        airport.getTerminals().forEach((i) -> {
            i.getFlights()
                    .stream()
                    .filter(time -> time.getDate().after(now))
                    .filter(time -> time.getDate().before(afterTwoHours))
                    .filter(departure -> departure.getType() == Flight.Type.DEPARTURE)
                    .forEach(flight -> planesLeavingInTheNextTwoHours.add(flight));
        });
        return planesLeavingInTheNextTwoHours;
    }
}