
import java.util.Scanner;

class CoachNode {
    String name;
    CoachNode prev;
    CoachNode next;

    CoachNode(String name) {
        this.name = name;
    }
}

class Train {
    private CoachNode head;
    private CoachNode tail;

    public void addLast(String name) {
        CoachNode node = new CoachNode(name);

        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public void remove(String name) {
        CoachNode current = head;

        while (current != null &&
               !current.name.equals(name)) {
            current = current.next;
        }

        if (current == null) {
            return;
        }

        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }

        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
    }

    public void printForward() {
        System.out.print("Forward: ");
        CoachNode current = head;

        while (current != null) {
            System.out.print(current.name);
            if (current.next != null) {
                System.out.print(" <-> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public void printBackward() {
        System.out.print("Backward: ");
        CoachNode current = tail;

        while (current != null) {
            System.out.print(current.name);
            if (current.prev != null) {
                System.out.print(" <-> ");
            }
            current = current.prev;
        }
        System.out.println();
    }
}

public class TrainCoachesBothWays {
    public static void main(String[] args) {
        Train train = new Train();

        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
