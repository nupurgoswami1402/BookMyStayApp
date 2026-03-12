import java.util.HashMap;

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory_1 {

    private final HashMap<String, Integer> inventory;

    public RoomInventory_1() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for " + roomType);
        }

        inventory.put(roomType, available - 1);

        System.out.println("Room booked successfully: " + roomType);
    }

    public void displayInventory() {

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + " Available: " + inventory.get(type));
        }
    }
}

class BookingValidator {

    public void validateRoomType(String roomType) throws InvalidBookingException {

        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }
    }
}

public class uc9 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 9.0     ");
        System.out.println("=====================================");

        RoomInventory inventory = new RoomInventory();
        BookingValidator validator = new BookingValidator();

        String[] requests = {
                "Single Room",
                "Suite Room",
                "Luxury Room",
                "",
                "Double Room"
        };

        for (String roomType : requests) {

            try {

                validator.validateRoomType(roomType);

                inventory.bookRoom(roomType);

            } catch (InvalidBookingException e) {

                System.out.println("Booking Failed: " + e.getMessage());
            }
        }

        inventory.displayInventory();
    }
}