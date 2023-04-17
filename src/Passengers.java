import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class Passengers {
    private Scanner input = new Scanner(System.in);
    private TreeMap<String, Flight> flightTreeMap;

    private void setFlightTreeMap() {
//        AdminControl adminControl = new AdminControl();
        this.flightTreeMap = (new AdminControl()).getFlightTreeMap();
    }

    private HashMap<Ticket, Passenger> reserved = new HashMap<>();
    private Passenger passenger = new Passenger();

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    private ArrayList<Passenger> passengers = new ArrayList<>();

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(ArrayList<Passenger> passengers) {
        this.passengers = passengers;
    }

    public void printPassengers() {
        System.out.println(passengers);
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(passengers.get(i).getUsername() + " " + passengers.get(i).getPassword());
        }
    }

    public void userMenu() {
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
        switch (option) {
            case 1 -> {
                changePassword();
                break;
            }
            case 2 -> {
                searchTickets();
                break;
            }
            case 3 -> {
                bookingTickets();
                break;
            }
            case 4 -> {
                cancelingTicket();
                break;
            }
            case 5 -> {
                showBookedTickets();
                break;
            }
            case 6 -> {
                addCharge();
                break;
            }
        }

    }

    private void bookingTickets() {
    }

    private void cancelingTicket() {
    }

    private void showBookedTickets() {
    }

    private void addCharge() {
    }

    private void searchTickets() {
        System.out.println("Search based on filters below: (Can choose one or some of them)");
        System.out.println("<1> FlightID\n<2> Origin\n<3> Destination\n<4> Date\n<5> Time\n<6> Price Range");

        ArrayList<Integer> option = new ArrayList<>();

    }

    private void changePassword() {
    }
}
