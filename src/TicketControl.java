import java.util.TreeMap;

public class TicketControl {
    private TreeMap<String, Flight> userTickets;

    public void setUserTickets() {
        this.userTickets = passenger.getTicket().getUserTickets();
    }

    public TreeMap<String, Flight> getUserTickets() {
        return userTickets;
    }

    private Passenger passenger;

    private String flightID;
    private String ticketID;

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
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
        if (takeCharge()) {
            takeSeat();
            return true;
        }
        return false;
    }

    private boolean takeCharge() {
        if (passenger.getCharge() >= flightTreeMap.get(flightID).getPrice()) {
            passenger.setCharge(passenger.getCharge() - flightTreeMap.get(flightID).getPrice());
        } else {
            System.out.printf("""
                    Your charge is not enough to book this flight.
                    You need at least %d$ more.
                    """, (flightTreeMap.get(flightID).getPrice()) - passenger.getCharge());
            return false;
        }
        return true;
    }

    private void takeSeat() {
        flightTreeMap.get(flightID).setSeat(flightTreeMap.get(flightID).getSeat() - 1);
    }

    public void availableSeats() {
        if (flightTreeMap.get(flightID).getSeat() == 0)
            flightTreeMap.remove(flightID);
    }

    public void cancelingTicket(Passenger passenger, String ticketID) {
        setPassenger(passenger);
        setUserTickets();
        setTicketID(ticketID);

        addCharge();
        addSeat();

        userTickets.remove(this.ticketID);
    }

    private void addCharge() {
        System.out.println(userTickets);
        passenger.setCharge(passenger.getCharge() + userTickets.get(ticketID).getPrice());
    }

    private void addSeat() {
        userTickets.get(ticketID).setSeat(userTickets.get(ticketID).getSeat() + 1);
    }
}
