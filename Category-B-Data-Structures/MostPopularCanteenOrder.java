import java.util.*;

public class MostPopularCanteenOrder {
    static Object[] findPopular(String[] orders) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String order : orders)
            counts.put(order, counts.getOrDefault(order, 0) + 1);

        String popular = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > max) {
                popular = entry.getKey();
                max = entry.getValue();
            }
        }
        return new Object[]{popular, max};
    }

    public static void main(String[] args) {
        String[] orders = {"dosa", "tea", "dosa", "idli", "tea", "dosa"};
        System.out.println(Arrays.toString(findPopular(orders)));
    }
}
