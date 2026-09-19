class MovieTicket {

    private String seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}

public class AccessCheckerDemo {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (fieldModifier.equals("private")) {
            return accessorContext.equals("SAME_CLASS")
                    ? "ALLOWED" : "DENIED";

        } else if (fieldModifier.equals("default")) {
            return accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";

        } else if (fieldModifier.equals("protected")) {
            return accessorContext.equals("DIFFERENT_PACKAGE")
                    ? "DENIED" : "ALLOWED";

        } else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (String[] attempt : attempts) {

            String result =
                    classifyAccess(attempt[0], attempt[1]);

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess("private", "SAME_CLASS")
        );

        System.out.println(
            classifyAccess("protected", "DIFFERENT_PACKAGE")
        );

        String[][] attempts = {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(attempts));
    }
}
