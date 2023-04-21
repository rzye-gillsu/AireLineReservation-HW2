import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Passengers {
    private Scanner input = new Scanner(System.in);
    private Passenger passenger;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private TicketControl ticketControl = new TicketControl();
    TreeMap<String, Flight> flightTreeMap;

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void printPassengers() {
        System.out.println(passengers);
        for (var p : passengers) {
            System.out.println(p.getUsername() + " " + p.getPassword());
        }
    }

    public void userMenu(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
        System.out.println("""
                ::::::::::::::::::::::::::::::::::::::::
                         PASSENGER MENU OPTIONS
                ::::::::::::::::::::::::::::::::::::::::
                 ......................................
                    <1> Change password
                    <2> Search flight tickets
                    <3> Booking ticket
                    <4> Ticket cancellation
                    <5> Booked tickets
                    <6> Add charge
                    <0> Sign out
                """);
        int option = input.nextInt();
        if (option == 0) {
            return; // is it needed?
        } else {
            switch (option) {
                case 1 -> changePassword();
                case 2 -> searchTickets();
                case 3 -> bookingTickets();
                case 4 -> cancelingTicket();
                case 5 -> showBookedTickets();
                case 6 -> addCharge();

            }
        }
        userMenu(flightTreeMap);

    }

    private void bookingTickets() {
        System.out.print("Your chosen flight's flightID: ");
        String flightID = input.next();

        if (passenger.getTicket().checkOption(flightID, flightTreeMap)) {
            passenger.getTicket().checkOption(flightID, flightTreeMap);
            ticketControl.setFlightTreeMap(flightTreeMap);
            if (ticketControl.manageTicket(passenger, flightID)) {
                passenger.getTicket().setTicketIDs(passenger, flightID);
                System.out.println("Ticket is saved!");
                ticketControl.availableSeats();
            }
        } else {
            System.out.println("\n!!!No flight with given flightID were found.");
        }

    }

    private void cancelingTicket() {
        ticketControl.cancelingTicket(passenger, passenger.getTicket().getTicketID());
    }

    private void showBookedTickets() {
    }

    private void addCharge() {
        System.out.print("how much you will charge your account?\n--> : ");
        passenger.setCharge(input.nextInt());
        System.out.printf("Your account is successfully charged.\nYour total charge is %d$\n", passenger.getCharge());
    }

    private void searchTickets() {
        System.out.print("""
                Search based on filters below:
                <1> FlightID
                <2> Origin
                <3> Destination
                <4> Date
                <5> Time
                <6> Price Range
                You can choose as many filters as you want. At the end, Submit your filters by pressing s
                ---> :""");

        ArrayList<Integer> options = gettingInputs();

        filters(options);

    }

    private ArrayList<Integer> gettingInputs() {
        ArrayList<Integer> options = new ArrayList<>();
        String option = " ";

        while (!option.equals("s")) {
            option = input.next();

            if (isInteger(option)) {

                if ((Integer.parseInt(option) > 0) && (Integer.parseInt(option) < 10)) {
                    System.out.println("\n!!!Choose an Integer between 1 - 9.");
                } else {
                    options.add(Integer.parseInt(option));
                }

            } else /*if (!option.equals("s"))*/ {
                System.out.println("\n!!!Choose an Integer type.");
            }
        }
        return options;
    }

    private void filters(ArrayList<Integer> options) {
        TreeMap<String, Flight> flightTreeMap = (new AdminControl()).getFlightTreeMap();
        for (var i : options) {
            for (var key : flightTreeMap.keySet()) {
                switch (i) {

                }
            }
        }
    }

    private void changePassword() {
        System.out.print("Enter your previous password: ");
        String password = input.next();
        if (password.equals(passenger.getPassword())) {
            System.out.print("Enter your new password: ");
            passenger.setPassword(input.next());
        } else {
            System.out.println("\n!!!It isn't your previous password. Try again later.");
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

