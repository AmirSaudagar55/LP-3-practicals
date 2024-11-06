import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int weight;
    int value;

    // Constructor
    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }
}

public class FractionalKnapsack {

    // Function to get the maximum total value in the knapsack
    public static double getMaxValue(Item[] items, int capacity) {
        // Sort items based on value-to-weight ratio in descending order
        Arrays.sort(items, new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                double r1 = (double) item1.value / item1.weight;
                double r2 = (double) item2.value / item2.weight;
                return Double.compare(r2, r1); // Sort in descending order
            }
        });

        double totalValue = 0.0;

        for (Item item : items) {
            if (capacity == 0) {
                break;
            }

            // If we can take the whole item, take it
            if (item.weight <= capacity) {
                capacity -= item.weight;
                totalValue += item.value;
            }
            // If we can only take a fraction of the item
            else {
                double fraction = (double) capacity / item.weight;
                totalValue += item.value * fraction;
                capacity = 0; // Knapsack is now full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input number of items
        System.out.print("Enter number of items: ");
        int n = scanner.nextInt();

        // Input capacity of knapsack
        System.out.print("Enter knapsack capacity: ");
        int capacity = scanner.nextInt();

        Item[] items = new Item[n];

        // Input weight and value for each item
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value for item " + (i + 1) + ": ");
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items[i] = new Item(weight, value);
        }

        // Calculate the maximum value achievable
        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value achievable: " + maxValue);

        scanner.close();
    }
}
