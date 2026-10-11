
import java.util.Scanner;

class OrderNode {
    int data;
    OrderNode next;

    OrderNode(int data) {
        this.data = data;
    }
}

public class CancelledOrdersCleanup {

    public static OrderNode removeAll(OrderNode head, int code) {
        // Remove matching nodes from the beginning
        while (head != null && head.data == code) {
            head = head.next;
        }

        OrderNode current = head;

        // Remove matching nodes from the remaining list
        while (current != null && current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        OrderNode head = null;
        OrderNode tail = null;

        // Enter order codes separated by spaces.
        // Enter an empty line for an empty list.
        String line = sc.nextLine().trim();

        if (!line.isEmpty()) {
            String[] values = line.split("\\s+");

            for (String value : values) {
                OrderNode node = new OrderNode(
                    Integer.parseInt(value));

                if (head == null) {
                    head = tail = node;
                } else {
                    tail.next = node;
                    tail = node;
                }
            }
        }

        int code = sc.nextInt();
        head = removeAll(head, code);

        OrderNode current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
        sc.close();
    }
}

