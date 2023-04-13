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
}
