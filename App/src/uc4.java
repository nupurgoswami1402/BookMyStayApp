import java.util.HashMap;

class RoomSearchService {

    private RoomInventory inventory;
    private HashMap<String, Room> rooms;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
        rooms = new HashMap<>();

        rooms.put("Single Room", new SingleRoom());
        rooms.put("Double Room", new DoubleRoom());
        rooms.put("Suite Room", new SuiteRoom());
    }

    public void searchAvailableRooms() {

        for (String type : rooms.keySet()) {

            int available = inventory.getAvailability(type);

            if (available > 0) {
                Room room = rooms.get(type);
                room.displayRoomDetails();
                System.out.println("Available: " + available);
                System.out.println();
            }
        }
    }
}

public class uc4 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 4.1     ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();

        inventory.registerRoomType("Single Room", 5);
        inventory.registerRoomType("Double Room", 3);
        inventory.registerRoomType("Suite Room", 0);

        RoomSearchService searchService = new RoomSearchService(inventory);

        System.out.println("\nAvailable Rooms\n");

        searchService.searchAvailableRooms();
    }
}