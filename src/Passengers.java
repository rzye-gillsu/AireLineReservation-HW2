import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passengers {
    private Scanner input = new Scanner(System.in);
    private Passenger passenger;
    private ArrayList<Passenger> passengers = new ArrayList<>();
    private TicketControl ticketControl = new TicketControl();
    TreeMap<String, Flight> flightTreeMap;
    TreeMap<String, Flight> userTickets;

    private void setUserTickets() {
        this.userTickets = ticketControl.getUserTickets();
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void userMenu(TreeMap<String, Flight> flightTreeMap) {
        this.flightTreeMap = flightTreeMap;
        if (passenger.updatingUser.get(passenger) != null) {
            System.out.println(passenger.updatingUser.get(passenger));
//            passenger.updatingUser.get(passenger) = null; // WHY???
        }
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
            return;
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
        System.out.print("Enter the ticketID you want to cancel: ");
        String ticketID = input.next();
        ticketControl.cancelingTicket(passenger, ticketID);
        System.out.printf("Ticket with ID: %s is successfully canceled.\n", ticketID);
    }

    private void showBookedTickets() {
        ticketControl.setUserTickets();
        setUserTickets();
        if (userTickets == null) {
            System.out.println("You don't have any booked tickets yet!");
        } else {
            System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                    "TicketID", "Origin", "Destination", "Date", "Time", "Price", "Seats");
            for (var key : userTickets.keySet()) {
                System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                        key, userTickets.get(key).getOrigin(),
                        userTickets.get(key).getDestination(), userTickets.get(key).getDate(),
                        userTickets.get(key).getTime(), userTickets.get(key).getPrice(), userTickets.get(key).getSeat());
            }
        }
    }

    private void addCharge() {
        System.out.printf("""
                You current charge is %d$
                how much you will charge your account?
                --> :\s""", passenger.getCharge());
        passenger.setCharge(passenger.getCharge() + input.nextInt());
        System.out.printf("Your account is successfully charged.\nYour total charge is %d$\n", passenger.getCharge());
    }

    private void searchTickets() {
        System.out.println("""
                Search based on filters below:
                <1> FlightID
                <2> Origin / Destination
                <3> Date
                <4> Time
                <5> Price
                You can choose as many filters as you want. At the end, Submit your filters by pressing s""");
        gettingInputs();

    }

    /**
     * It's an internal method that get the desired inputs to search the flights.
     */
    private void gettingInputs() {
        ArrayList<String> flights = turnToString();

        String option = "q";
        while (!option.equals("s")) {
            System.out.print("---The Filter you want(or press 's' to submit): ");
            option = input.next();
            if (isInteger(option)) {

                if ((Integer.parseInt(option) < 1) && (Integer.parseInt(option) > 9)) {
                    System.out.println("\n!!!Choose an Integer between 1 - 9.");
                } else {
                    filters(Integer.parseInt(option), flights);
                }

            } else if (!option.equals("s")) {
                System.out.println("\n!!!Choose an Integer type.");
            }
        }

        if (flights == null) {
            System.out.println("Inputs didn't match in any flight.");
        } else {
            System.out.println("\nThe searched Flight(s):");
            System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                    "FlightID", "Origin", "Destination", "Date", "Time", "Price", "Seats");
            for (String flight : flights) {
                String[] flightID = flight.split(" ");
                printSearchedFlights(flightID[0]);
            }
        }
    }

    private void printSearchedFlights(String flightID) {
        System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                flightID, flightTreeMap.get(flightID).getOrigin(),
                flightTreeMap.get(flightID).getDestination(), flightTreeMap.get(flightID).getDate(),
                flightTreeMap.get(flightID).getTime(), flightTreeMap.get(flightID).getPrice(), flightTreeMap.get(flightID).getSeat());

    }

    /**
     * By this internal method user can submit their desired feature of the selected filter to search.
     *
     * @param option  the filter
     * @param flights flights turned into strings
     */
    private void filters(int option, ArrayList<String> flights) {
        String word = null;
        int max = 0, min = 0;
        switch (option) {
            case 1 -> {
                System.out.print("The flightID: ");
                word = input.next();
            }
            case 2 -> {
                System.out.print("The Origin/ Destination: ");
                word = input.next();
            }
            case 3 -> {
                System.out.print("The Date, Submit->\n\tThe Year, the month and the day seperated with space:");
                word = input.nextInt() + "-" + input.nextInt() + "-" + input.nextInt();
            }
            case 4 -> {
                System.out.print("The Time, submit->\n\tThe hour and the minute seperated with space: ");
                word = input.nextInt() + ":" + input.nextInt();
            }
            case 5 -> {
                System.out.print("The Price: ");
                String temp = input.next();
                if (isInteger(temp)) {
                    word = temp;
                } else {
                    System.out.println("You have to enter an Integer type");
                }
            }
        }

        search(word, flights);
    }

    /**
     * It's the main internal method that do the searching.
     * It uses regex to match user's input with flights strings.
     *
     * @param word    user's input
     * @param flights flights turned into strings
     */
    private void search(String word, ArrayList<String> flights) {
        String regex = "\\b" + word + "\\b";

        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for (int i = 0; i < flights.size(); i++) {
            matcher = pattern.matcher(flights.get(i));
            if (!matcher.find()) {
                flights.remove(i);
                i--;
            }
        }
    }

    /**
     * It turns all the flights to seperated strings and get stored in an ArrayList<String> flights.
     *
     * @return flights turned to strings
     */
    private ArrayList<String> turnToString() {
        ArrayList<String> flights = new ArrayList<>();
        for (var key : flightTreeMap.keySet()) {
            flights.add(key + " *** " + flightTreeMap.get(key).getOrigin() + " " + flightTreeMap.get(key).getDestination() + " " +
                    flightTreeMap.get(key).getDate() + " " + flightTreeMap.get(key).getTime() + " " + flightTreeMap.get(key).getPrice() + " " + flightTreeMap.get(key).getSeat());
        }
        return flights;
    }


    private void changePassword() {
        System.out.print("Enter your previous password: ");
        String password = input.next();
        if (password.equals(passenger.getPassword())) {
            System.out.print("Enter your new password: ");
            password = input.next();
            if (password.length() < 4) {
                System.out.println("\n!!!Password must contain at least 4 characters. Try it again.");
            } else {
                passenger.setPassword(password);
            }
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

