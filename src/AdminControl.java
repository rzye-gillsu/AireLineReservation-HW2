import java.util.HashMap;

public class AdminControl {
    private HashMap<String, Flight> flightHashMap;

    public void preActions() {
        Flight[] flights = new Flight[10];
        for (int i = 0; i < 10; i++) {
            flights[i] = new Flight();
        }
        flightHashMap = flights[0].setPrimaryFlights(flights);
    }

    public void print() {
        System.out.println(flightHashMap);
    }
}
