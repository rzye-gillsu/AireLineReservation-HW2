import java.util.TreeMap;

public class AdminControl {
    private TreeMap<String, Flight> flightTreeMap;

    /**
     * Flights are defined.
     */
    public void setPrimaryData() {
        Flight[] flights = new Flight[10];
        for (int i = 0; i < 10; i++) {
            flights[i] = new Flight();
        }
        flightTreeMap = flights[0].setPrimaryFlights(flights);
    }

    public TreeMap<String, Flight> getFlightTreeMap() {
        return flightTreeMap;
    }

    /**
     * It prints the flights chart by admins command.
     *
     * @param flightTreeMap
     */
    public void printFlightsChart(TreeMap<String, Flight> flightTreeMap) {

        System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                "FlightID", "Origin", "Destination", "Date", "Time", "Price", "Seats");

        for (String key : flightTreeMap.keySet()) {
            System.out.printf("|%-7s\t|%-10s\t|%-12s\t|%-9s\t|%-6s\t|%-8s\t|%-4s\n",
                    key, flightTreeMap.get(key).getOrigin(),
                    flightTreeMap.get(key).getDestination(), flightTreeMap.get(key).getDate(),
                    flightTreeMap.get(key).getTime(), flightTreeMap.get(key).getPrice(), flightTreeMap.get(key).getSeat());
        }
    }

    /**
     * It's filling the flights treemap.
     *
     * @param flightID as the key
     * @param flight   as the value
     */
    public void binding(String flightID, Flight flight) {
        flightTreeMap.put(flightID, flight);
    }

}
