import java.util.ArrayList;
import java.util.HashMap;

public class Flight {
    private String flightID;
    private String origin;
    private String destination;
    private String date;
    private String time;
    private int price;
    private int seat;

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getPrice() {
        return price;
    }

    public int getSeat() {
        return seat;
    }

    public String getFlightID() {
        return flightID;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(int year, int month, int day) {
        date = year + "-" + month + "-" + day;
    }

    public void setTime(int hour, int minute) {
        time = hour + ":" + minute;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public HashMap<String, Flight> setPrimaryFlights(Flight[] flights) {
        ArrayList<String> IDs = new ArrayList<>();
        setIDsArraylist(IDs);

        HashMap<String, Flight> flightHashMap = new HashMap<>();
        setFlights(flights, flightHashMap, IDs);

        return flightHashMap;
    }

    private void setIDsArraylist(ArrayList<String> IDs) {
        IDs.add("HW2--0");
        IDs.add("HW2--1");
        IDs.add("HW2--2");
        IDs.add("HW2--3");
        IDs.add("HW2--4");
        IDs.add("HW2--5");
        IDs.add("HW2--6");
        IDs.add("HW2--7");
        IDs.add("HW2--8");
        IDs.add("HW2--9");
    }

    private void setFlights(Flight[] flights, HashMap<String, Flight> flightHashMap, ArrayList<String> IDs) {

        String[] cities = new String[]{"Yazd", "Tehran", "Esfahan", "Mashhad", "Tabriz",
                "Yasooj", "Shiraz", "Kerman", "Ilam", "Mazandaran"};
        int[] prices = new int[]{700000, 900000, 800000, 1300000, 1000000, 1000000, 1300000, 800000, 900000, 700000};

        int increment = 0, decrement = 9;

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 0, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 10, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 20, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 30, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 40, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 3, 30, 8, 50, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 4, 1, 10, 10, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 4, 1, 10, 20, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement--], 4, 1, 10, 30, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment++]);

        primaryFeatures(flights[increment], cities[increment], cities[decrement], 4, 1, 10, 40, prices[increment]);
        flightHashMap.put(IDs.get(increment), flights[increment]);

    }

    private void primaryFeatures(Flight flight, String origin, String destination, int month, int day,
                                 int hour, int minute, int price) {
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDate(2023, month, day);
        flight.setTime(hour, minute);
        flight.setPrice(price);
        flight.setSeat(245);
    }
}
