import java.util.TreeMap;

public class TicketControl {
    private TreeMap<String, Flight> userTickets;
    private Passenger passenger;
    private String flightID;
    private String ticketID;
    private TreeMap<String, Flight> flightTreeMap;

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setFlightTreeMap(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public void setUserTickets() {
        this.userTickets = passenger.getTicket().getUserTickets();
    }

    public TreeMap<String, Flight> getUserTickets() {
        return userTickets;
    }

    /**
     * Manages number of seats and decreasing user's price when reserving a ticket.
     *
     * @param passenger
     * @param flightID
     * @return -> returns true if user's charge is enough to pay for the ticket.
     */
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

    /**
     * Manages number of seats and increasing user's price when canceling a ticket.
     * @param passenger
     * @param ticketID
     */
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
