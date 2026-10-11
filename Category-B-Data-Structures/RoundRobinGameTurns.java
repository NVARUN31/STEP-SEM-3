
import java.util.Scanner;

class CircularLinkedList {
    private static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    private Node tail;

    public void addLast(String name) {
        Node newNode = new Node(name);

        if (tail == null) {
            tail = newNode;
            tail.next = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    public void printTurns(int turns) {
        if (tail == null) return;

        Node current = tail.next;

        for (int i = 0; i < turns; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(current.name);
            current = current.next;
        }

        System.out.println();
    }
}

public class RoundRobinGameTurns {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CircularLinkedList players = new CircularLinkedList();

        String[] names = sc.nextLine().split(",");

        for (String name : names) {
            players.addLast(name.trim());
        }

        int turns = sc.nextInt();
        players.printTurns(turns);

        sc.close();
    }
}
