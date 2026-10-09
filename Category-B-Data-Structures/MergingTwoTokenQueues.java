import java.util.*;

public class MergingTwoTokenQueues {
    static List<Integer> merge(int[] a, int[] b) {
        List<Integer> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) result.add(a[i++]);
            else result.add(b[j++]);
        }
        while (i < a.length) result.add(a[i++]);
        while (j < b.length) result.add(b[j++]);
        return result;
    }

    public static void main(String[] args) {
        int[] a = {3, 8, 12, 20};
        int[] b = {5, 8, 15};
        System.out.println(merge(a, b));
    }
}
