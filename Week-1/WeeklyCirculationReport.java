class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
        this.booksBorrowed = 0;
    }

    public String displayInfo() {
        return "General | Books: " + booksBorrowed;
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String displayInfo() {
        return "Student | Course: " + course +
               " | Books: " + booksBorrowed;
    }
}

public class WeeklyCirculationReport {

    static String batchPrint(LibraryMember[] members) {
        StringBuilder result = new StringBuilder();

        for (LibraryMember member : members) {
            result.append(member.displayInfo());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;

                result.append(" [Course via downcast: ")
                      .append(student.getCourse())
                      .append("]");
            }

            result.append(" | ");
        }

        return result.toString();
    }

    public static void main(String[] args) {
        LibraryMember general =
            new LibraryMember("LB5", 3);

        StudentMember student =
            new StudentMember("STU6", 3, "ECE");

        System.out.println(
            batchPrint(new LibraryMember[]{general, student})
        );
    }
}
