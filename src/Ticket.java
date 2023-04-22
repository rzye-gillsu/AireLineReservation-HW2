import java.util.TreeMap;

public class Ticket {
    private Passenger passenger;
    private String flightID;
    private TreeMap<String, Flight> flightTreeMap;
    private TreeMap<String, Flight> userTickets = new TreeMap<>();
    private String ticketID;

    public TreeMap<String, Flight> getUserTickets() {
        return userTickets;
    }

    private void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
    }

    private static int i;
    private void setTicketID() {
        ticketID = passenger.getUsername() + i++ + flightID;
    }

    public void setTicketIDs(Passenger passenger, String flightID) {
        this.passenger = passenger;
        this.flightID = flightID;
        setTicketID();
        setUserTickets();
    }

    private void setUserTickets() {
        userTickets.put(ticketID, flightTreeMap.get(flightID));
    }

    public boolean checkOption(String flightID, TreeMap<String, Flight> flightTreeMap) {
        setFlightTreeMap(flightTreeMap);
        int check = 0;
        for (var key : flightTreeMap.keySet())
            if (flightID.equals(key)) {
                check = 1;
                break;
            }
        return check != 0;
    }
}
