import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Ticket {
    private Passenger passenger;
    private String flightID;
    private TreeMap<String, Flight> flightTreeMap;
    private HashMap<Passenger, ArrayList<Flight>> ticketHashMap = new HashMap<>();
    private ArrayList<Flight> flights = new ArrayList<>();
    private String ticketID;

    public String getTicketID() {
        return ticketID;
    }
    AdminControl adminControl = new AdminControl();
    private void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
//        AdminControl adminControl = new AdminControl();
        this.flightTreeMap = flightTreeMap;
    }

    private void setTicketID() {
        ticketID = passenger.getUsername().concat(flightID);
    }

    public HashMap<Passenger, ArrayList<Flight>> getTicketHashMap() {
        return ticketHashMap;
    }

    public void setTicketIDs(Passenger passenger, String flightID) {
        this.passenger = passenger;
        this.flightID = flightID;
        setFlights();
        setTicketHashMap();
    }

    private void setFlights() {
//        ticketHashMap.get(passenger).add(flightTreeMap.get(flightID));
        flights.add(flightTreeMap.get(flightID));
    }

    private void setTicketHashMap() {
        ticketHashMap.put(passenger, flights);

        for (var key : ticketHashMap.keySet()) {
            System.out.println(key + " " + flights);
        }
    }

    public boolean checkOption(String flightID, TreeMap<String, Flight> flightTreeMap) {
        setFlightTreeMap(flightTreeMap);
        int check = 0;
        for (var key : flightTreeMap.keySet())
            if (flightID.equals(key))
                check = 1;
        if (check == 0)
            return false;
        return true;
    }
}
