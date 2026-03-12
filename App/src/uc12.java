import java.io.*;
import java.util.*;

/* Reservation class */
class Reservation implements Serializable {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void display() {
        System.out.println("Guest: " + guestName + " | Room: " + roomType);
    }
}

/* System state that will be saved to file */
class SystemState implements Serializable {

    public HashMap<String, Integer> inventory;
    public List<Reservation> bookings;

    public SystemState() {

        inventory = new HashMap<>();
        bookings = new ArrayList<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 2);
        inventory.put("Suite Room", 1);
    }
}

/* Handles saving and loading data */
class PersistenceService {

    private static final String FILE_NAME = "hotelData.ser";

    public static void save(SystemState state) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            out.writeObject(state);
            out.close();

            System.out.println("\nSystem state saved to file.");

        } catch (IOException e) {
            System.out.println("Error while saving data.");
        }
    }

    public static SystemState load() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            SystemState state = (SystemState) in.readObject();
            in.close();

            System.out.println("System state restored from file.");

            return state;

        } catch (Exception e) {

            System.out.println("No saved data found. Starting new system.");

            return new SystemState();
        }
    }
}

/* Main Application */
public class uc12 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("   Book My Stay - Version 12.0       ");
        System.out.println("=====================================");

        SystemState state = PersistenceService.load();

        System.out.println("\nCurrent Inventory:");

        for (Map.Entry<String, Integer> entry : state.inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }

        /* Create new reservations */
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");

        state.bookings.add(r1);
        state.bookings.add(r2);

        state.inventory.put("Single Room",
                state.inventory.get("Single Room") - 1);

        state.inventory.put("Double Room",
                state.inventory.get("Double Room") - 1);

        System.out.println("\nBookings Added:");

        for (Reservation r : state.bookings) {
            r.display();
        }

        PersistenceService.save(state);
    }
}