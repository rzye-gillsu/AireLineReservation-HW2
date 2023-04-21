import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class TicketControl {
    private Ticket ticket;
    private Passenger passenger;
    private String flightID;
    private HashMap<Passenger, ArrayList<Flight>> ticketHashMap;

    public void setTicketHashMap() {
        this.ticketHashMap = (new Ticket()).getTicketHashMap();
    }

    private TreeMap<String, Flight> flightTreeMap;

    public void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public boolean manageTicket(Passenger passenger, String flightID) {
        setPassenger(passenger);
        setFlightID(flightID);
        if (manageCharge() && manageSeat())
            return true;
        return false;
    }

    private boolean manageCharge() {
        if (passenger.getCharge() >= flightTreeMap.get(flightID).getPrice()) {
            passenger.setCharge(passenger.getCharge() - flightTreeMap.get(flightID).getPrice());
        } else {
            System.out.printf("Your charge is not enough to book this flight.\n" +
                    "You need at least %d$ more.\n", (flightTreeMap.get(flightID).getPrice()) - passenger.getCharge());
            return false;
        }
        return true;
    }

    private boolean manageSeat() {
        if (flightTreeMap.get(flightID).getSeat() == 0) {
            System.out.println("This Flight has no seats available.");
            return false;
        } else {
            flightTreeMap.get(flightID).setSeat(flightTreeMap.get(flightID).getSeat() - 1);
        }
        return true;
    }
}
