
import java.util.Arrays;
import java.util.Scanner;

public class NoticeBoardShuffle {

    public static int[] bubbleStats(int[] scores) {
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < scores.length - 1; i++) {
            boolean swapped = false;
            passes++;

            for (int j = 0; j < scores.length - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;

                    swaps++;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return new int[]{passes, swaps};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of scores: ");
        int n = sc.nextInt();

        int[] scores = new int[n];

        System.out.println("Enter scores:");
        for (int i = 0; i < n; i++) {
            scores[i] = sc.nextInt();
        }

        int[] result = bubbleStats(scores);

        System.out.println("Sorted scores: " + Arrays.toString(scores));
        System.out.println("Passes: " + result[0]);
        System.out.println("Swaps: " + result[1]);

        sc.close();
    }
}
