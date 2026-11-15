import java.util.Scanner;

class Node {
    String data;
    Node next;

    Node(String data) {
        this.data = data;
        this.next = null;
    }
}

public class LastButKAnnouncement {

    public static String kthFromEnd(Node head, int k) {
        Node lead = head;
        Node trail = head;

        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return null;
            }
            lead = lead.next;
        }

        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        return trail.data;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of announcements: ");
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        System.out.println("Enter announcement IDs:");

        for (int i = 0; i < n; i++) {
            String id = sc.next();

            Node newNode = new Node(id);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        String result = kthFromEnd(head, k);

        if (result == null) {
            System.out.println("Invalid k");
        } else {
            System.out.println(k + " from the end: " + result);
        }

        sc.close();
    }
}