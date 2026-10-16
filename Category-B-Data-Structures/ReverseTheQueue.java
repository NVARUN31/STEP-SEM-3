
import java.util.Scanner;

class QueueNode {
    int data;
    QueueNode next;

    QueueNode(int data) {
        this.data = data;
    }
}

public class ReverseTheQueue {

    public static QueueNode reverse(QueueNode head) {
        QueueNode previous = null;
        QueueNode current = head;

        while (current != null) {
            QueueNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }

        return previous;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        QueueNode head = null;
        QueueNode tail = null;

        String line = sc.nextLine().trim();

        if (!line.isEmpty()) {
            String[] values = line.split("\\s+");

            for (String value : values) {
                QueueNode node =
                    new QueueNode(Integer.parseInt(value));

                if (head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
            }
        }

        head = reverse(head);

        QueueNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
        sc.close();
    }
}
