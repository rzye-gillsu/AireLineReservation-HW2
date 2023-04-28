package SerializingTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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
            fileInputStream = new FileInputStream("data.ser");
            ois = new ObjectInputStream(fileInputStream);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void readFile(TreeMap flightTreeMap, ArrayList passengers) {
        try {
            flightTreeMap = (TreeMap) ois.readObject();
            passengers = (ArrayList) ois.readObject();
            ois.close();
            fileInputStream.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
    }

    public boolean isEmpty() throws IOException {
        if (fileInputStream.available() == 0)
            return true;
        return false;
    }

}
