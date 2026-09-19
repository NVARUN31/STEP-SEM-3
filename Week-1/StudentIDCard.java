public class StudentIDCard {

    String studentName;
    int cardNumber;

    StudentIDCard(String studentName, int cardNumber) {
        this.studentName = studentName;
        this.cardNumber = cardNumber;
    }

    public static void main(String[] args) {

        StudentIDCard original =
            new StudentIDCard("Rahul", 101);

        StudentIDCard copy = original;

        copy.cardNumber = 202;

        System.out.println("Card number via original: " +
                original.cardNumber);

        System.out.println("copy == original: " +
                (copy == original));

        StudentIDCard separate =
            new StudentIDCard("Rahul", 202);

        System.out.println("separate == original: " +
                (separate == original));
    }
}
