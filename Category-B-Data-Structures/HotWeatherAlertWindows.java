public class HotWeatherAlertWindows {
    static int countWindows(int[] temperatures, int k, int threshold) {
        if (k <= 0 || k > temperatures.length) return 0;

        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += temperatures[i];
        }

        int count = sum >= k * threshold ? 1 : 0;

        for (int i = k; i < temperatures.length; i++) {
            sum += temperatures[i] - temperatures[i - k];
            if (sum >= k * threshold) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] temperatures = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countWindows(temperatures, 3, 4));
    }
}
