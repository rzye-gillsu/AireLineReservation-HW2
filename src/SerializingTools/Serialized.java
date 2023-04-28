package SerializingTools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.TreeMap;

public class Serialized {
    private static Serialized instance = new Serialized();
    public static Serialized getInstance() {
        return instance;
    }
    private FileOutputStream fileOutputStream;
    private ObjectOutputStream oos;

    private Serialized() {
        try {
            fileOutputStream = new FileOutputStream("flights.ser");
            oos = new ObjectOutputStream(fileOutputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeFile(Object flightTreeMap, Object passengers) {
        try {
            oos.writeObject(flightTreeMap);
            oos.writeObject(passengers);
            oos.close();
            fileOutputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
