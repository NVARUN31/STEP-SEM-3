abstract class Vehicle {
    private final String id;
    private boolean available = true;

    Vehicle(String id) {
        this.id = id;
    }

    String getId() {
        return id;
    }

    boolean isAvailable() {
        return available;
    }

    abstract double calculateCharge(int days);

    void rent() {
        available = false;
    }

    void returnVehicle() {
        available = true;
    }
}

class Sedan extends Vehicle {
    Sedan(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 50.0;
    }
}

class SUV extends Vehicle {
    SUV(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 80.0;
    }
}

class Truck extends Vehicle {
    Truck(String id) {
        super(id);
    }

    double calculateCharge(int days) {
        return days * 120.0;
    }
}

class Customer {
    private final String name;

    Customer(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Rental {
    private final Vehicle vehicle;
    private final Customer customer;
    private boolean active = true;

    Rental(Vehicle vehicle, Customer customer) {
        this.vehicle = vehicle;
        this.customer = customer;
    }

    void returnVehicle() {
        if (!active) return;

        vehicle.returnVehicle();
        active = false;
        System.out.println(vehicle.getId() + " returned by "
                + customer.getName() + ".");
    }
}

class RentalService {
    Rental rentVehicle(Vehicle vehicle, Customer customer, int days) {
        if (days <= 0) {
            System.out.println("Rental days must be positive.");
            return null;
        }

        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId()
                    + " is currently unavailable.");
            return null;
        }

        vehicle.rent();
        Rental rental = new Rental(vehicle, customer);

        System.out.println(vehicle.getId()
                + " rented successfully by " + customer.getName());
        System.out.printf("Rental charge: $%.2f%n",
                vehicle.calculateCharge(days));

        return rental;
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        RentalService service = new RentalService();

        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Rental rental = service.rentVehicle(sedan, c1, 3);
        service.rentVehicle(sedan, c2, 2);

        if (rental != null) rental.returnVehicle();

        service.rentVehicle(suv, c3, 5);
    }
}
