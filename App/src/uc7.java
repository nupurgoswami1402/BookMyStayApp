import java.util.*;

record Service(String serviceName, double price) {

}

class AddOnServiceManager {

    private final Map<String, List<Service>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);

        System.out.println(service.serviceName() +
                " added to reservation " + reservationId);
    }

    public void displayServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected for reservation " + reservationId);
            return;
        }

        System.out.println("\nServices for Reservation " + reservationId + ":");

        for (Service s : services) {
            System.out.println("- " + s.serviceName() + " : $" + s.price());
        }
    }

    public double calculateTotalCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.price();
            }
        }

        return total;
    }
}

public class uc7 {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("      Book My Stay - Version 7.0     ");
        System.out.println("=====================================");

        String reservationId = "RES-101";

        Service breakfast = new Service("Breakfast", 15);
        Service airportPickup = new Service("Airport Pickup", 30);
        Service spa = new Service("Spa Access", 50);

        AddOnServiceManager manager = new AddOnServiceManager();

        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);
        manager.addService(reservationId, spa);

        manager.displayServices(reservationId);

        double total = manager.calculateTotalCost(reservationId);

        System.out.println("\nTotal Add-On Cost: $" + total);
    }
}