import java.util.*;

interface Seat {
    String getId();
    double getPrice();
}

class RegularSeat implements Seat {
    private final String id;
    RegularSeat(String id) { this.id = id; }
    public String getId() { return id; }
    public double getPrice() { return 150; }
}

class PremiumSeat implements Seat {
    private final String id;
    PremiumSeat(String id) { this.id = id; }
    public String getId() { return id; }
    public double getPrice() { return 250; }
}

class ReclinerSeat implements Seat {
    private final String id;
    ReclinerSeat(String id) { this.id = id; }
    public String getId() { return id; }
    public double getPrice() { return 400; }
}

class Customer {
    private final String name;
    Customer(String name) { this.name = name; }
    String getName() { return name; }
}

class Show {
    private boolean started = false;
    private final Map<String, Booking> booked = new HashMap<>();

    boolean hasStarted() { return started; }
    void startShow() { started = true; }

    boolean available(List<Seat> seats) {
        Set<String> ids = new HashSet<>();
        for (Seat s : seats) {
            if (!ids.add(s.getId()) || booked.containsKey(s.getId()))
                return false;
        }
        return true;
    }

    void reserve(Booking b) {
        for (Seat s : b.getSeats()) booked.put(s.getId(), b);
    }

    void release(Booking b) {
        for (Seat s : b.getSeats()) booked.remove(s.getId(), b);
    }

    boolean isAvailable(Seat s) {
        return !booked.containsKey(s.getId());
    }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean cancelled = false;

    Booking(Customer c, Show s, List<Seat> seats) {
        customer = c;
        show = s;
        this.seats = new ArrayList<>(seats);
    }

    List<Seat> getSeats() { return seats; }

    void confirm() {
        if (cancelled) {
            System.out.println("Booking has been cancelled.");
            return;
        }
        if (seats.isEmpty() || seats.size() > 6) {
            System.out.println("A booking must contain 1 to 6 seats.");
            return;
        }
        if (!show.available(seats)) {
            Set<String> seen = new HashSet<>();
            for (Seat s : seats) {
                if (!seen.add(s.getId())) {
                    System.out.println("Duplicate seat: " + s.getId());
                    return;
                }
                if (!show.isAvailable(s)) {
                    System.out.println("Seat " + s.getId()
                        + " is already booked for this show.");
                    return;
                }
            }
            return;
        }

        show.reserve(this);
        StringJoiner ids = new StringJoiner(", ");
        double total = 0;
        for (Seat s : seats) {
            ids.add(s.getId());
            total += s.getPrice();
        }
        System.out.println("Booking confirmed for " + customer.getName()
            + ": " + ids + ".");
        System.out.printf("Total: ₹%.2f.%n", total);
    }

    void cancel() {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
            return;
        }
        if (show.hasStarted()) {
            System.out.println("Cannot cancel: show has already started.");
            return;
        }
        show.release(this);
        cancelled = true;
        StringJoiner ids = new StringJoiner(", ");
        for (Seat s : seats) ids.add(s.getId());
        System.out.println(customer.getName() + "'s booking cancelled.");
        System.out.println("Seats " + ids + " released.");
    }
}

public class CampusPremiereTicketCounter {
    public static void main(String[] args) {
        Show show = new Show();
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking b1 = new Booking(asha, show, Arrays.asList(
            new RegularSeat("A1"), new RegularSeat("A2"),
            new PremiumSeat("F5")));
        b1.confirm();

        new Booking(ravi, show,
            Collections.singletonList(new RegularSeat("A2"))).confirm();

        new Booking(ravi, show,
            Collections.singletonList(new ReclinerSeat("R1"))).confirm();

        b1.cancel();

        new Booking(neha, show,
            Collections.singletonList(new RegularSeat("A2"))).confirm();
    }
}
