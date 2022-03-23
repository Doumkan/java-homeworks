import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //TODO: find all flights taking off in the next two hours
    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {

        Date time = new Date();
        time.setHours(time.getHours() + 2);

        return airport.getTerminals().stream().flatMap(term -> term.getFlights()
                        .stream())
                .filter(flight -> flight.getDate()
                        .before(time))
                .filter(flight -> flight.getDate()
                        .after(new Date()))
                .filter(flight -> flight.getType()
                        .equals(Flight.Type.DEPARTURE))
                .collect(Collectors.toList());
    }
}
