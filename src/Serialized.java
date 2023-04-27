import java.io.*;
import java.util.TreeMap;

public class Serialized {
    private Admin admin = Admin.getInstance();
    private TreeMap flightTreeMap;
    FileOutputStream fileOutputStream;

    public Serialized() {
        try {
            fileOutputStream = new FileOutputStream("flights.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeFile() {
        flightTreeMap = admin.getFlightTreeMap();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(fileOutputStream);
            oos.writeObject(flightTreeMap);
            oos.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public TreeMap<String, Flight> readFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("flights.ser");
            ObjectInputStream ois = new ObjectInputStream(fileInputStream);
            flightTreeMap = (TreeMap) ois.readObject();
            ois.close();
            fileInputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return flightTreeMap;
    }
}
