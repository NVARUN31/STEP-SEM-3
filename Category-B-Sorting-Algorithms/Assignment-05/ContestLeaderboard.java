
import java.util.Scanner;

class Entry {
    String name;
    int marks;

    Entry(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
}

public class ContestLeaderboard {

    public static Entry[] leaderboard(Entry[] entries) {
        int[] count = new int[101];

        for (Entry entry : entries) {
            int key = 100 - entry.marks;
            count[key]++;
        }

        for (int i = 1; i < 101; i++) {
            count[i] += count[i - 1];
        }

        Entry[] result = new Entry[entries.length];

        for (int i = entries.length - 1; i >= 0; i--) {
            int key = 100 - entries[i].marks;
            result[--count[key]] = entries[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        Entry[] entries = new Entry[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter student name: ");
            String name = sc.next();

            System.out.print("Enter marks: ");
            int marks = sc.nextInt();

            entries[i] = new Entry(name, marks);
        }

        Entry[] result = leaderboard(entries);

        System.out.println("Contest Leaderboard:");

        for (Entry entry : result) {
            System.out.println(entry.name + " " + entry.marks);
        }

        sc.close();
    }
}
