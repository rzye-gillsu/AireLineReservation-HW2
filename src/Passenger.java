import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

public class Passenger {
    private AdminControl adminControl = new AdminControl();
    private TreeMap<String, Flight> flightTreeMap;

    private void setFlightTreeMap() {
        this.flightTreeMap = adminControl.getFlightTreeMap();
    }

    private Scanner input = new Scanner(System.in);

    public void usermenu() {
        System.out.println("""
                ::::::::::::::::::::::::::::::::::::::::
                         PASSENGER MENU OPTIONS
                ::::::::::::::::::::::::::::::::::::::::
                 ......................................
                    <1> Change password
                    <2> Search flight tickets
                    <3> Booking ticket
                    <4> Ticket cancelation
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
