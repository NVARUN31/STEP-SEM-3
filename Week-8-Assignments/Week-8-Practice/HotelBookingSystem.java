import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

class Room {
    int number;
    String type;
    double price;

    Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
    }
}

class Reservation {
    Room room;
    String guest;
    LocalDate checkIn, checkOut;
    boolean cancelled = false;

    Reservation(Room room, String guest, LocalDate in, LocalDate out) {
        this.room = room;
        this.guest = guest;
        checkIn = in;
        checkOut = out;
    }

    void display() {
        long nights = ChronoUnit.DAYS.between(checkIn, checkOut);
        System.out.println("Guest: " + guest);
        System.out.println("Room: " + room.number + " (" + room.type + ")");
        System.out.println("Stay: " + checkIn + " to " + checkOut);
        System.out.printf("Total price: $%.2f%n", nights * room.price);
    }
}

class Hotel {
    List<Room> rooms = new ArrayList<>();
    List<Reservation> reservations = new ArrayList<>();

    Hotel() {
        rooms.add(new Room(101, "Standard", 100));
        rooms.add(new Room(102, "Deluxe", 180));
        rooms.add(new Room(103, "Suite", 300));
    }

    Reservation bookRoom(int number, String guest, LocalDate in, LocalDate out) {
        if (!out.isAfter(in)) {
            System.out.println("Invalid booking dates.");
            return null;
        }

        Room selected = null;
        for (Room room : rooms) {
            if (room.number == number) {
                selected = room;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Room not found.");
            return null;
        }

        for (Reservation r : reservations) {
            boolean overlap = in.isBefore(r.checkOut) && out.isAfter(r.checkIn);
            if (r.room.number == number && !r.cancelled && overlap) {
                System.out.println("Room " + number + " is already booked for those dates.");
                return null;
            }
        }

        Reservation r = new Reservation(selected, guest, in, out);
        reservations.add(r);
        System.out.println("Booking successful!");
        r.display();
        return r;
    }

    void cancelReservation(Reservation r) {
        if (r == null || r.cancelled) {
            System.out.println("Invalid or already cancelled reservation.");
            return;
        }

        if (LocalDate.now().plusDays(2).isAfter(r.checkIn)) {
            System.out.println("Cancellation deadline has passed.");
            return;
        }

        r.cancelled = true;
        System.out.println("Reservation cancelled for " + r.guest);
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        LocalDate in = LocalDate.now().plusDays(10);
        LocalDate out = in.plusDays(3);

        Reservation first = hotel.bookRoom(101, "Varun", in, out);
        hotel.bookRoom(101, "Arun", in.plusDays(1), out.plusDays(1));
        hotel.bookRoom(102, "Priya", in, out);
        hotel.cancelReservation(first);
    }
}
