import java.util.Scanner;
import java.util.TreeMap;

public class Admin {
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
    Serialized srz = new Serialized();

    /**
     * Primary values are set.
     */
    private Admin() {
        flightTreeMap = srz.readFile();
        if (flightTreeMap == null) {
            adminControl.setPrimaryData();
            flightTreeMap = adminControl.getFlightTreeMap();
        }
    }

    public void adminMenu() {
        int option = 1;
        while (option != 0) {
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
            option = input.nextInt();
            option = getOption(option);
            switch (option) {
                case 1 -> add();
                case 2 -> update();
                case 3 -> remove();
                case 4 -> flightSchedule();
            }
        }
    }

    private int getOption(int option) {
        while (option > 4 || option < 0) {
            System.out.print("Wrong input! try another option  in range (1, 4): ");
            option = input.nextInt();
        }
        return option;
    }

    /**
     * This method enables admin to add a new flight to the chart.
     */
    private void add() {
        Flight flight = new Flight();

        System.out.print("Flight ID(Enter the code which is a number): ");
        flight.setFlightID(input.next());

        System.out.print("Flight's Origin: ");
        flight.setOrigin(input.next());

        System.out.print("Flight's Destination: ");
        flight.setDestination(input.next());

        System.out.print("Date of the Flight, Submit->\n\tThe Year, the month and the day seperated with space: ");
        flight.setDate(input.nextInt(), input.nextInt(), input.nextInt());

        System.out.print("Time of the Flight, submit->\n\tThe hour and the minute seperated with space: ");
        flight.setTime(input.nextInt(), input.nextInt());

        System.out.print("Price of the Flight: ");
        flight.setPrice(input.nextInt());

        System.out.print("Number of the Seats: ");
        flight.setSeat(input.nextInt());

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
                System.out.print("Date of the Flight, Submit->\n\tThe Year, the month and the day seperated with space: ");
                flightTreeMap.get(ID).setDate(input.nextInt(), input.nextInt(), input.nextInt());
            }
            case 5 -> {
                System.out.print("Time of the Flight, submit->\n\tThe hour and the minute seperated with space: ");
                flightTreeMap.get(ID).setTime(input.nextInt(), input.nextInt());
            }
            case 6 -> {
                System.out.print("The Price of the Flight: ");
                flightTreeMap.get(ID).setPrice(input.nextInt());
            }
            case 7 -> {
                System.out.print("Number of the Seats: ");
                flightTreeMap.get(ID).setSeat(input.nextInt());
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
