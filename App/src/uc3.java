import java.util.HashMap;
import java.util.Map;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
    }

    public void registerRoomType(String type, int count) {
        inventory.put(type, count);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

public class uc3 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 3.1     ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Single Room", 5);
        inventory.registerRoomType("Double Room", 3);
        inventory.registerRoomType("Suite Room", 2);

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        single.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Single Room"));

        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Double Room"));

        suite.displayRoomDetails();
        System.out.println("Available: " + inventory.getAvailability("Suite Room"));

        System.out.println("\nCurrent Inventory");
        inventory.displayInventory();
    }
}