class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public String getMembershipPin() {
        return membershipPin;
    }
}

class StudentMember extends LibraryMember {

    public StudentMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        super(membershipPin, branchCode, finesOwed, displayName);
    }

    public String accessFields() {
        return "Protected fines: " + finesOwed
                + " | Public name: " + displayName;
    }
}

public class SubclassMembershipAccess {

    static String classifyAccess(String fieldModifier, String context) {
        if (fieldModifier.equals("private")) {
            return "DENIED";
        }

        if (fieldModifier.equals("default")) {
            return context.equals("SAME_PACKAGE")
                    ? "ALLOWED" : "DENIED";
        }

        if (fieldModifier.equals("protected")) {
            return "ALLOWED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {

        StudentMember student =
            new StudentMember("1234", "MAIN", 250.0, "Rahul");

        System.out.println(student.accessFields());

        System.out.println(
            classifyAccess("private", "SUBCLASS")
        );

        System.out.println(
            classifyAccess("protected", "SUBCLASS")
        );
    }
}
