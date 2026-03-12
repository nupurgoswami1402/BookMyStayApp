import java.util.*;

class Reservation {

    private final String guestName;
    private final String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayReservation() {
    }
}

class RoomInvent {

    private final HashMap<String, Integer> inventory;

    public RoomInvent() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void decreaseRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available: " + entry.getValue());
        }
    }
}

class BookingQueue {

    private final Queue<Reservation> queue;

    public BookingQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation r) {
        queue.offer(r);
        System.out.println("Booking request added for " + r.getGuestName());
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

class RoomAllocationService {

    public final Set<String> allocatedRoomIds = new HashSet<>();
    private final HashMap<String, Set<String>> roomAllocations = new HashMap<>();
    private int roomCounter = 1;

    public void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

        while (queue.hasRequests()) {

            Reservation r = queue.getNextRequest();
            String type = r.getRoomType();

            if (inventory.getAvailability(type) > 0) {

                String roomId = "ROOM-" + roomCounter++;
                allocatedRoomIds.add(roomId);

                roomAllocations.putIfAbsent(type, new HashSet<>());
                roomAllocations.get(type).add(roomId);

                inventory.decreaseRoom(type);

                System.out.println("\nReservation Confirmed");
                System.out.println("Guest: " + r.getGuestName());
                System.out.println("Room Type: " + type);
                System.out.println("Allocated Room ID: " + roomId);

            } else {

                System.out.println("\nNo rooms available for " + type +
                        " (Guest: " + r.getGuestName() + ")");
            }
        }
    }
}

public class uc6 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 6.0     ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Single Room"));
        queue.addRequest(new Reservation("Charlie", "Double Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));
        queue.addRequest(new Reservation("Eva", "Suite Room"));

        RoomAllocationService service = new RoomAllocationService();
        service.processBookings(queue, inventory);

        inventory.displayInventory();
    }
}