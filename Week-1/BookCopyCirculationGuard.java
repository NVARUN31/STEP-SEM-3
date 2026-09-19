class BookInventory {
    private int copiesTotal;
    private int copiesAvailable;
    private int checkedOut;

    public BookInventory(int copiesTotal) {
        if (copiesTotal <= 0 || copiesTotal > 500) {
            throw new IllegalArgumentException("Invalid copies total");
        }

        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
        this.checkedOut = 0;
    }

    public void checkOut() {
        if (copiesAvailable > 0) {
            copiesAvailable--;
            checkedOut++;
        }
    }

    public void checkIn() {
        if (checkedOut > 0) {
            copiesAvailable++;
            checkedOut--;
        }
    }

    public int getCopiesAvailable() {
        return copiesAvailable;
    }
}

public class BookCopyCirculationGuard {
    public static void main(String[] args) {
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(b.getCopiesAvailable());
    }
}
