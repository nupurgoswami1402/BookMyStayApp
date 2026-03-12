import java.util.*;

record Reservation(String reservationId, String guestName, String roomType) {

    public void display() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

public class RoomInventory {

    private final HashMap<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void increaseRoom(String type) {
        inventory.put(type, inventory.get(type) + 1);
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

class CancellationService {

    private final HashMap<String, Reservation> confirmedBookings = new HashMap<>();
    private final Stack<String> rollbackStack = new Stack<>();

    public void confirmBooking(Reservation r, RoomInventory inventory) {

        confirmedBookings.put(r.reservationId(), r);
        inventory.decreaseRoom(r.roomType());

        rollbackStack.push(r.reservationId());

        System.out.println("Booking Confirmed:");
        r.display();
    }

    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed: Reservation does not exist.");
            return;
        }

        Reservation r = confirmedBookings.remove(reservationId);

        rollbackStack.push(reservationId);

        inventory.increaseRoom(r.roomType());

        System.out.println("Booking Cancelled:");
        r.display();
    }
}

public class uc10 {

    static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 10.0    ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService();

        Reservation r1 = new Reservation("RES-201", "Alice", "Single Room");
        Reservation r2 = new Reservation("RES-202", "Bob", "Double Room");

        service.confirmBooking(r1, inventory);
        service.confirmBooking(r2, inventory);

        inventory.displayInventory();

        System.out.println("\nCancelling Reservation RES-201");

        service.cancelBooking("RES-201", inventory);

        inventory.displayInventory();
    }
}