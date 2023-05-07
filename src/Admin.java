import java.util.Scanner;
import java.util.TreeMap;

public class Admin extends Subject {
    /**
     * implemented SingleTone.
     *
     * @return an instance of type Admin
     */
    public static Admin getInstance() {
        return instance;
    }

    private static Admin instance = new Admin();
    private Scanner input = new Scanner(System.in);
    private AdminControl adminControl = new AdminControl();
    private TreeMap<String, Flight> flightTreeMap;
    Flight flight = new Flight();

    /**
     * Primary values are set.
     */
    private Admin() {
        adminControl.setPrimaryData();
        flightTreeMap = adminControl.getFlightTreeMap();
    }

    public void adminMenu() {
        String option;
        int o = 1;
        while (o != 0) {
            System.out.println("""
                    ::::::::::::::::::::::::::::::::::::::::
                               Admin MENU OPTIONS
                    ::::::::::::::::::::::::::::::::::::::::
                     ......................................
                        <1> Add
                        <2> Update
                        <3> Remove
                        <4> Flight schedules
                        <0> Sign out""");
            System.out.print("Your option(1-4): ");
            option = input.next();
            o = InputHandler.getInstance().checkAdminMenuInput(option);
            switch (o) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> remove();
                case 4 -> flightSchedule();
            }
        }
    }

    /**
     * This method enables admin to add a new flight to the chart.
     */
    private void add() {
        Flight flight = new Flight();

        System.out.print("Flight ID(Enter the code which is a number): ");
        flight.setFlightID(input.next());

        {
            System.out.print("Flight's Origin: ");
            flight.setOrigin(input.next());
        }

        {
            System.out.print("Flight's Destination: ");
            flight.setDestination(input.next());
        }

        {
            System.out.print("Date of the Flight, Submit->\n\tThe Year, the month and the day as xxxx-xx-xx: ");
            String date = input.next();
            boolean dateIsValid = InputHandler.getInstance().checkDateFormat(date);
            while (!dateIsValid) {
                System.out.print("Try again: ");
                dateIsValid = InputHandler.getInstance().checkDateFormat(input.next());
            }
            flight.setDate(date);
        }

        {
            System.out.print("Time of the Flight, submit->\n\tThe hour and the minute as xx:xx: ");
            String time = input.next();
            boolean timeIsValid = InputHandler.getInstance().checkTimeFormat(time);
            while (!timeIsValid) {
                System.out.print("Try again: ");
                timeIsValid = InputHandler.getInstance().checkTimeFormat(input.next());
            }
            flight.setTime(time);
        }

        {
            System.out.print("Price of the flight: ");
            String price = InputHandler.getInstance().checkIt(input);
            flight.setPrice(Integer.parseInt(price));
        }

        {
            System.out.print("Number of the Seats: ");
            String seat = InputHandler.getInstance().checkIt(input);
            flight.setSeat(Integer.parseInt(seat));
        }

        adminControl.binding(flight.getFlightID(), flight);
    }

    /**
     * This method enables admin to update a feature each time.
     */
    private void update() {
        System.out.print("The Flight ID you wanna update: ");
        String ID = input.next();

        System.out.println("""
                Which feature you wanna update?(enter a number)
                  (1) flightID
                  (2) Origin
                  (3) Destination
                  (4) Date
                  (5) Time
                  (6) Price
                  (7) Seat""");
        System.out.print("Your option: ");
        int choice = input.nextInt();

        updatingFeatures(ID, choice);
        notifyObserver(ID);

    }

    /**
     * It's an internal function here to help update method.
     *
     * @param ID     is the ID of the flight, admin want to update.
     * @param choice is the feature they want to update.
     */
    private void updatingFeatures(String ID, int choice) {
        switch (choice) {
            case 1 -> {
                System.out.print("The Flight ID(Enter the code which is a number): ");
                flightTreeMap.get(ID).setFlightID(input.next());
                flight = flightTreeMap.remove(ID);
                flightTreeMap.put(flight.getFlightID(), flight);
            }
            case 2 -> {
                System.out.print("The Flight's Origin: ");
                flightTreeMap.get(ID).setOrigin(input.next());
            }
            case 3 -> {
                System.out.print("The Flight's Destination: ");
                flightTreeMap.get(ID).setDestination(input.next());
            }
            case 4 -> {
                System.out.print("Date of the Flight, Submit->\n\tThe Year, the month and the day as xxxx-xx-xx: ");
                String date = input.next();
                if (InputHandler.getInstance().checkDateFormat(date))
                    flightTreeMap.get(ID).setDate(date);
            }
            case 5 -> {
                System.out.print("Time of the Flight, submit->\n\tThe hour and the minute as xx:xx: ");
                String time = input.next();
                if (InputHandler.getInstance().checkTimeFormat(time))
                    flightTreeMap.get(ID).setTime(time);
            }
            case 6 -> {
                System.out.print("Price of the flight: ");
                String price = InputHandler.getInstance().checkIt(input);
                flightTreeMap.get(ID).setPrice(Integer.parseInt(price));
            }
            case 7 -> {
                System.out.print("Number of the Seats: ");
                String seat = InputHandler.getInstance().checkIt(input);
                flightTreeMap.get(ID).setSeat(Integer.parseInt(seat));
            }
        }
    }

    /**
     * It enables admin to remove a flight of the chart.
     */
    private void remove() {
        System.out.print("The Flight ID(Enter the code which is a number):");
        String ID = input.next();
        flightTreeMap.remove(ID);
        notifyObserver(ID);
    }

    /**
     * It prints the whole chart.
     */
    private void flightSchedule() {
        flightTreeMap = adminControl.getFlightTreeMap();
        adminControl.printFlightsChart(flightTreeMap);
    }

    public TreeMap<String, Flight> getFlightTreeMap() {
        return flightTreeMap;
    }

}
