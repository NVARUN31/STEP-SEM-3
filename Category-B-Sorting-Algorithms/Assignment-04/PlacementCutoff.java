
import java.util.Scanner;

public class PlacementCutoff {

    public static int partition(int[] marks, int low, int high) {
        int pivot = marks[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (marks[j] < pivot) {
                i++;

                int temp = marks[i];
                marks[i] = marks[j];
                marks[j] = temp;
            }
        }

        int temp = marks[i + 1];
        marks[i + 1] = marks[high];
        marks[high] = temp;

        return i + 1;
    }

    public static int kthHighest(int[] marks, int k) {
        int target = marks.length - k;
        int low = 0;
        int high = marks.length - 1;

        while (low <= high) {
            int pivotIndex = partition(marks, low, high);

            if (pivotIndex == target) {
                return marks[pivotIndex];
            } else if (pivotIndex < target) {
                low = pivotIndex + 1;
            } else {
                high = pivotIndex - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of applicants: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        System.out.println("Enter marks:");
        for (int i = 0; i < n; i++) {
            marks[i] = sc.nextInt();
        }

        System.out.print("Enter k: ");
        int k = sc.nextInt();

        if (k < 1 || k > n) {
            System.out.println("Invalid k value");
        } else {
            System.out.println("K-th highest mark: " + kthHighest(marks, k));
        }

        sc.close();
    }
}
