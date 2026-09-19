abstract class DeliveryNote {
    protected String trackingId;

    public DeliveryNote(String trackingId) {
        if (trackingId == null || trackingId.isBlank()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank");
        }
        this.trackingId = trackingId;
    }

    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        return confirmDelivery() + " | Signed by: " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    public ParcelNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    public LetterNote(String trackingId) {
        super(trackingId);
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}

public class PackageDropOffLog {
    public static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        DeliveryNote parcel = new ParcelNote("TRK-1");
        DeliveryNote letter = new LetterNote("TRK-2");

        System.out.println(parcel.confirmDelivery());
        System.out.println(parcel.confirmDelivery("Ravi"));
        System.out.println(letter.confirmDelivery());

        DeliveryNote[] notes = {parcel, letter};
        logAll(notes);
    }
}
