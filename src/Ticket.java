import java.util.ArrayList;
import java.util.TreeMap;

public class Ticket {
    private Passenger passenger;
    private String flightID;
    private TreeMap<String, Flight> flightTreeMap;
    private TreeMap<String, Flight> userTickets = new TreeMap<>();
    private static ArrayList<String> flightIDs = new ArrayList<>();

    public static boolean flightIdFound(String ID) {
        for (var id : flightIDs) {
            if (ID.equals(id))
                return true;
        }
        return false;
    }

    private String ticketID;

    public TreeMap<String, Flight> getUserTickets() {
        return userTickets;
    }

    private void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
    }

    public void setFlights(TreeMap<String, Flight> flightTreeMap) {
        setFlightTreeMap(flightTreeMap);
    }

    private static int i;

    private void setTicketID() {
        ticketID = passenger.getUsername() + i++ + flightID;
    }

    /**
     * It sets the ticket for the customer and generates a specialized ticketID.
     *
     * @param passenger
     * @param flightID
     */
    public void setTicketIDs(Passenger passenger, String flightID) {
        this.passenger = passenger;
        this.flightID = flightID;
        flightIDs.add(flightID);
        setTicketID();
        setUserTickets();
    }

    private void setUserTickets() {
        userTickets.put(ticketID, flightTreeMap.get(flightID));
    }

    /**
     * Is an internal method to check whether the user's chosen flight exists or not.
     *
     * @param ID
     * @param treeMap
     * @return -> returns true if the flight is reservable.
     */
    public boolean checkOption(String ID, TreeMap<String, Flight> treeMap) {
        int check = 0;
        for (var key : treeMap.keySet())
            if (ID.equals(key)) {
                check = 1;
                break;
            }
        return check != 0;
    }
}
