import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passenger implements Observer {
    private String username;
    private String password;
    private int charge;
    private Ticket ticket;

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashMap<Passenger, String> updatingUser = new HashMap<>();
    @Override
    public void update(String ID) {
//        System.out.printf("""
//                Flight's control has changed or removed the flightID %s which was reserved by you.
//                To check for new updates search %s
//                """, ID, ID);
        Pattern pattern = Pattern.compile(ID, Pattern.CASE_INSENSITIVE);
        for (var ticketID : this.getTicket().getUserTickets().keySet()) {
            Matcher matcher = pattern.matcher(ticketID);
            if (matcher.find()) {
                updatingUser.put(this, String.format("Flight's control has changed or removed the flightID %s which was reserved by you.\n" +
                        "To check for new updates search %s", ID, ID));
                (new TicketControl()).cancelingTicket(this, ticketID);
            }
        }
    }

}
