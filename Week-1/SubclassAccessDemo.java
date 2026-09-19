class PremiumMovieTicket extends MovieTicket {
}

public class SubclassAccessDemo {

    static String classifyAccess(
            String fieldModifier,
            String accessorContext) {

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

            if (fieldModifier.equals("protected")
                    || fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {

            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"
            )
        );

        System.out.println(
            classifyAccess(
                "protected",
                "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"
            )
        );
    }
}
