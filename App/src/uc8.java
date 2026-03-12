import java.util.ArrayList;
import java.util.List;

record usecase6(String reservationId, String guestName, String roomType) {

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room Type: " + roomType);
    }
}

class BookingHistory {

    private final List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addBooking(Reservation reservation) {
        history.add(reservation);
    }

    public List<Reservation> getHistory() {
        return history;
    }

    public void displayHistory() {

        System.out.println("\nBooking History:");

        for (Reservation r : history) {
            r.displayReservation();
        }
    }
}

class BookingReportService {

    public void generateReport(List<Reservation> history) {

        System.out.println("\nBooking Report Summary");

        int totalBookings = history.size();
        int single = 0;
        int doubleRoom = 0;
        int suite = 0;

        for (Reservation r : history) {

            switch (r.roomType()) {
                case "Single Room" -> single++;
                case "Double Room" -> doubleRoom++;
                case "Suite Room" -> suite++;
            }
        }

        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Single Rooms Booked: " + single);
        System.out.println("Double Rooms Booked: " + doubleRoom);
        System.out.println("Suite Rooms Booked: " + suite);
    }
}

public class uc8 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 8.0     ");
        System.out.println("=====================================");

        BookingHistory history = getBookingHistory();

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getHistory());
    }

    private static BookingHistory getBookingHistory() {
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("RES-101", "Alice", "Single Room");
        Reservation r2 = new Reservation("RES-102", "Bob", "Double Room");
        Reservation r3 = new Reservation("RES-103", "Charlie", "Suite Room");
        Reservation r4 = new Reservation("RES-104", "David", "Single Room");

        history.addBooking(r1);
        history.addBooking(r2);
        history.addBooking(r3);
        history.addBooking(r4);

        history.displayHistory();
        return history;
    }
}