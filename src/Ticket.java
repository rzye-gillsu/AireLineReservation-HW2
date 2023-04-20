import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Ticket {
    private Passenger passenger;
    private String flightID;
    private TreeMap<String, Flight> flightTreeMap;
    private HashMap<Passenger, ArrayList<Flight>> ticketHashMap = new HashMap<>();
    private ArrayList<Flight> flights = new ArrayList<>();

    public void setTicketIDs(Passenger passenger, String flightID) {
        this.passenger = passenger;
        this.flightID = flightID;
        setFlights();
        setTicketTreeMap();
    }

    private void setFlights() {
        setFlightTreeMap();
        flights.add(flightTreeMap.get(flightID));
    }

    private void setTicketTreeMap() {
        ticketHashMap.put(passenger, flights);

        for (var key : ticketHashMap.keySet()) {
            System.out.println(key + " " + flights);
        }
    }

    private void setFlightTreeMap() {
//        AdminControl adminControl = new AdminControl();
        AdminControl adminControl = new AdminControl();
        flightTreeMap = adminControl.getFlightTreeMap();
    }
}