import java.util.ArrayList;
import java.util.List;

public class Subject {
    private List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObserver(String ID) {
        for (var observer : observers) {
            if (Ticket.flightIdFound(ID))
                observer.update(ID);
        }
    }
}
