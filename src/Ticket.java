import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Ticket {
    private Passenger passenger;
    private String flightID;
    private TreeMap<String, Flight> flightTreeMap;
    private HashMap<Passenger, TreeMap<String, Flight>> ticketHashMap = new HashMap<>();
    private TreeMap<String, Flight> userTickets = new TreeMap<>();
    private String ticketID;

    public String getTicketID() {
        return ticketID;
    }

    public TreeMap<String, Flight> getUserTickets(Passenger passenger) {
        return ticketHashMap.get(passenger);
    }

    private void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
    }

    private void setTicketID() {
        ticketID = passenger.getUsername().concat(flightID);
    }

    public HashMap<Passenger, TreeMap<String, Flight>> getTicketHashMap() {
        return ticketHashMap;
    }

    public void setTicketIDs(Passenger passenger, String flightID) {
        this.passenger = passenger;
        this.flightID = flightID;
        setTicketID();
        setUserTickets();
        setTicketHashMap();

    }

    private void setUserTickets() {
        userTickets.put(ticketID, flightTreeMap.get(flightID));
    }

    private void setTicketHashMap() {
        ticketHashMap.put(passenger, userTickets);

        for (var key : ticketHashMap.keySet()) {
            System.out.println(key + " " + userTickets);
        }
    }

    public boolean checkOption(String flightID, TreeMap<String, Flight> flightTreeMap) {
        setFlightTreeMap(flightTreeMap);
        int check = 0;
        for (var key : this.flightTreeMap.keySet())
            if (flightID.equals(key))
                check = 1;
        if (check == 0)
            return false;
        return true;
    }
}
