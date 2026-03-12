import java.util.LinkedList;
import java.util.Queue;

record usecase5(String guestName, String roomType) {

    public void displayReservation() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

class BookingRequestQueue {

    private final Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.guestName());
    }

    public void displayQueue() {
        System.out.println("\nCurrent Booking Requests (FIFO Order):");
        for (Reservation r : queue) {
            r.displayReservation();
        }
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return false;
    }
}

public class uc5 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 5.1     ");
        System.out.println("=====================================");

        BookingRequestQueue requestQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Alice", "Single Room", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room", "Single Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room", "Single Room");

        requestQueue.addRequest(r1);
        requestQueue.addRequest(r2);
        requestQueue.addRequest(r3);

        requestQueue.displayQueue();
    }
}