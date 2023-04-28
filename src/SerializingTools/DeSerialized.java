package SerializingTools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.TreeMap;

public class DeSerialized {
    private static DeSerialized instance = new DeSerialized();
    public static DeSerialized getInstance() {
        return instance;
    }
    private FileInputStream fileInputStream;
    private ObjectInputStream ois;
    private DeSerialized() {
        try {
            fileInputStream = new FileInputStream("flights.ser");
            ois = new ObjectInputStream(fileInputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public TreeMap readFile(Object flightTreeMap) {
        try {
            flightTreeMap = (TreeMap) ois.readObject();
            ois.close();
            fileInputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return (TreeMap) flightTreeMap;
    }

    public boolean isEmpty() throws IOException {
        if (fileInputStream.available() == 0)
            return true;
        return false;
    }

}
