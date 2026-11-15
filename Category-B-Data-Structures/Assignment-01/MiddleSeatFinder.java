
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class MiddleSeatFinder {

    public static int middle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow.data;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of seats: ");
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        System.out.println("Enter seat numbers:");

        for (int i = 0; i < n; i++) {
            int value = sc.nextInt();
            Node newNode = new Node(value);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        System.out.println("Middle: " + middle(head));

        sc.close();
    }
}
