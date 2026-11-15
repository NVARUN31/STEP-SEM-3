import java.util.Scanner;

class TabNode {
    String name;
    TabNode prev;
    TabNode next;

    TabNode(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

public class BrowserTabStrip {

    static TabNode head = null;
    static TabNode current = null;

    public static void open(String name) {
        TabNode newTab = new TabNode(name);

        if (current == null) {
            head = newTab;
            current = newTab;
            return;
        }

        newTab.prev = current;
        newTab.next = current.next;

        if (current.next != null) {
            current.next.prev = newTab;
        }

        current.next = newTab;
        current = newTab;
    }

    public static String close() {
        if (current == null) {
            return "";
        }

        String closedName = current.name;

        if (current.next != null) {
            TabNode nextTab = current.next;

            nextTab.prev = current.prev;

            if (current.prev != null) {
                current.prev.next = nextTab;
            } else {
                head = nextTab;
            }

            current = nextTab;
        } else {
            TabNode previousTab = current.prev;

            if (previousTab != null) {
                previousTab.next = null;
            } else {
                head = null;
            }

            current = previousTab;
        }

        return closedName;
    }

    public static boolean back() {
        if (current == null || current.prev == null) {
            return false;
        }

        current = current.prev;
        return true;
    }

    public static boolean forward() {
        if (current == null || current.next == null) {
            return false;
        }

        current = current.next;
        return true;
    }

    public static void printTabs() {
        TabNode temp = head;

        while (temp != null) {
            if (temp == current) {
                System.out.print("[" + temp.name + "]");
            } else {
                System.out.print(temp.name);
            }

            if (temp.next != null) {
                System.out.print(" <-> ");
            }

            temp = temp.next;
        }

        System.out.println();
    }

    public static void printBackward() {
        if (head == null) {
            System.out.println("Backward: ");
            return;
        }

        TabNode temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        System.out.print("Backward: ");

        while (temp != null) {
            System.out.print(temp.name);

            if (temp.prev != null) {
                System.out.print(" <-> ");
            }

            temp = temp.prev;
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of commands: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            String command = sc.next().toUpperCase();

            switch (command) {
                case "OPEN":
                    String name = sc.next();
                    open(name);
                    System.out.print("OPEN " + name + ": ");
                    printTabs();
                    break;

                case "CLOSE":
                    String closedName = close();
                    System.out.print("CLOSE " + closedName + ": ");
                    printTabs();
                    break;

                case "BACK":
                    if (back()) {
                        System.out.print("BACK: ");
                        printTabs();
                    } else {
                        System.out.println("BACK: already at first tab");
                    }
                    break;

                case "FORWARD":
                    if (forward()) {
                        System.out.print("FORWARD: ");
                        printTabs();
                    } else {
                        System.out.println("FORWARD: already at last tab");
                    }
                    break;

                case "PRINT":
                    String direction = sc.next();
                    if (direction.equalsIgnoreCase("BACKWARD")) {
                        printBackward();
                    }
                    break;

                default:
                    System.out.println("Invalid command");
            }
        }

        sc.close();
    }
}