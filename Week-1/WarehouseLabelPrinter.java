interface Printable {
    String printLabel();
}

class PackageBox implements Printable {
    private String trackingId;

    public PackageBox(String trackingId) {
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}

class Invoice implements Printable {
    private String invoiceId;

    public Invoice(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public String printLabel() {
        return "Invoice label: " + invoiceId;
    }
}

public class WarehouseLabelPrinter {
    public static void printAll(Printable[] items) {
        for (Printable item : items) {
            System.out.println(item.printLabel());
        }
    }

    public static void main(String[] args) {
        Printable[] items = {
            new PackageBox("TRK-88"),
            new Invoice("INV-42")
        };

        printAll(items);
    }
}
