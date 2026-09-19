import java.util.Scanner;

public class PalindromeChecker {

    // Method 1: Iterative approach
    static boolean isPalindromeIterative(String text) {

        int left = 0;
        int right = text.length() - 1;

        while (left < right) {

            if (text.charAt(left) != text.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    // Method 2: Recursive approach
    static boolean isPalindromeRecursive(String text) {

        if (text.length() <= 1) {
            return true;
        }

        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }

        return isPalindromeRecursive(
                text.substring(1, text.length() - 1)
        );
    }

    // Method 3: Array reversal approach
    static boolean isPalindromeArrayReversal(String text) {

        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];

        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }

        return new String(original).equals(new String(reversed));
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a word or text: ");
        String text = scanner.nextLine();

        System.out.println("\n===== PALINDROME CHECKER =====");

        System.out.println("Iterative: " +
                (isPalindromeIterative(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        System.out.println("Recursive: " +
                (isPalindromeRecursive(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        System.out.println("Array Reversal: " +
                (isPalindromeArrayReversal(text)
                        ? "Palindrome"
                        : "Not Palindrome"));

        scanner.close();
    }
}