import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    String getTitle() { return title; }
    int getMaxMarks() { return maxMarks; }

    long getLateDays(LocalDate date) {
        return Math.max(0, ChronoUnit.DAYS.between(dueDate, date));
    }

    abstract double applyPenalty(double marks, long days);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String t, int m, LocalDate d) {
        super(t, m, d);
    }

    double applyPenalty(double marks, long days) {
        return marks * Math.max(0, 1 - 0.10 * days);
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String t, int m, LocalDate d) {
        super(t, m, d);
    }

    double applyPenalty(double marks, long days) {
        return marks * Math.max(0, 1 - 0.20 * days);
    }
}

class Student {
    private final String name;

    Student(String name) { this.name = name; }
    String getName() { return name; }
}

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate date;
    private String status = "Submitted";

    Submission(Student s, Assignment a, LocalDate d) {
        student = s;
        assignment = a;
        date = d;
    }

    String getStatus() { return status; }

    void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade: already graded.");
            return;
        }

        if (marks < 0 || marks > assignment.getMaxMarks()) {
            System.out.println("Invalid marks awarded.");
            return;
        }

        long days = assignment.getLateDays(date);
        double result = assignment.applyPenalty(marks, days);
        status = "Graded";

        if (days == 0) {
            System.out.printf("%s graded: %.0f/%d.%n",
                student.getName(), result, assignment.getMaxMarks());
        } else {
            double penalty = marks == 0 ? 0 : (marks - result) / marks * 100;
            System.out.printf("%s graded: %.0f/%d after %.0f%% late penalty.%n",
                student.getName(), result, assignment.getMaxMarks(), penalty);
        }
        System.out.println("Status: " + status + ".");
    }

    void resubmit() {
        if (status.equals("Graded")) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle()
                + "' has already been graded.");
        } else {
            System.out.println("Resubmission received.");
        }
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment coding = new CodingAssignment("Linked List Lab", 50,
            LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment("Design Essay", 50,
            LocalDate.of(2026, 3, 12));

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission s1 = new Submission(asha, coding,
            LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission(ravi, written,
            LocalDate.of(2026, 3, 14));

        System.out.println("Asha's submission for 'Linked List Lab' received (on time).");
        System.out.println("Status: " + s1.getStatus() + ".");
        System.out.println("Ravi's submission for 'Design Essay' received (2 days late).");
        System.out.println("Status: " + s2.getStatus() + ".");

        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}
