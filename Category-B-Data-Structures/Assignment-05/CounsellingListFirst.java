
import java.util.Scanner;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class CounsellingListFirst {

    public static Node partition(Node head, int passMark) {
        Node belowHead = null;
        Node belowTail = null;
        Node aboveHead = null;
        Node aboveTail = null;

        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = null;

            if (current.data < passMark) {
                if (belowHead == null) {
                    belowHead = current;
                    belowTail = current;
                } else {
                    belowTail.next = current;
                    belowTail = current;
                }
            } else {
                if (aboveHead == null) {
                    aboveHead = current;
                    aboveTail = current;
                } else {
                    aboveTail.next = current;
                    aboveTail = current;
                }
            }

            current = nextNode;
        }

        if (belowHead == null) {
            return aboveHead;
        }

        belowTail.next = aboveHead;

        return belowHead;
    }

    public static void display(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        Node head = null;
        Node tail = null;

        System.out.println("Enter marks:");

        for (int i = 0; i < n; i++) {
            int marks = sc.nextInt();
            Node newNode = new Node(marks);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        System.out.print("Enter pass mark: ");
        int passMark = sc.nextInt();

        head = partition(head, passMark);

        System.out.println("Rearranged list:");
        display(head);

        sc.close();
    }
}
