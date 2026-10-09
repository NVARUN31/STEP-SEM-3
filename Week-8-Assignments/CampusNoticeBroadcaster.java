import java.util.*;

interface NotificationChannel {
    void send(Student student, Notice notice);
}

class EmailChannel implements NotificationChannel {
    public void send(Student s, Notice n) {
        System.out.println("[Email → " + s.getName() + "] " + n.getTitle());
    }
}

class SmsChannel implements NotificationChannel {
    public void send(Student s, Notice n) {
        System.out.println("[SMS → " + s.getName() + "] " + n.getTitle());
    }
}

class AppChannel implements NotificationChannel {
    public void send(Student s, Notice n) {
        System.out.println("[App → " + s.getName() + "] " + n.getTitle());
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> channels;

    Student(String name, String department,
            List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = channels;
    }

    String getName() { return name; }
    String getDepartment() { return department; }
    List<NotificationChannel> getChannels() { return channels; }
}

class Notice {
    private final String title;
    private final Set<String> departments;

    Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Notice title cannot be empty.");
        if (departments == null || departments.isEmpty())
            throw new IllegalArgumentException(
                "At least one target department is required.");

        this.title = title;
        this.departments = new LinkedHashSet<>(departments);
    }

    String getTitle() { return title; }
    Set<String> getDepartments() { return departments; }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    void addStudent(Student student) {
        students.add(student);
    }

    void postNotice(String title, Set<String> departments) {
        try {
            Notice notice = new Notice(title, departments);

            System.out.println("Notice '" + title + "' posted to "
                + String.join(", ", departments) + ".");

            for (Student student : students) {
                if (notice.getDepartments().contains(student.getDepartment())) {
                    for (NotificationChannel channel : student.getChannels()) {
                        channel.send(student, notice);
                    }
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        board.addStudent(new Student("Asha", "CSE",
            Arrays.asList(new EmailChannel(), new AppChannel())));

        board.addStudent(new Student("Ravi", "ECE",
            Collections.singletonList(new SmsChannel())));

        board.postNotice("Lab Closed Tomorrow",
            new LinkedHashSet<>(Arrays.asList("CSE")));

        board.postNotice("Fee Deadline Extended",
            new LinkedHashSet<>(Arrays.asList("CSE", "ECE")));

        board.postNotice("Sports Day", Collections.emptySet());
    }
}
