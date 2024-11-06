import java.util.Scanner;

public class KnapsackDP {

    // Function to solve the 0-1 Knapsack problem using dynamic programming
    public static int knapsack(int[] weights, int[] values, int capacity, int n) {
        int[][] dp = new int[n + 1][capacity + 1];

        // Build the dp table
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    // Max of including the item or excluding it
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // Item cannot be included
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Return the maximum value for the full capacity
        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of items
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        // Input weights and values of each item
        for (int i = 0; i < n; i++) {
            System.out.print("Enter weight and value for item " + (i + 1) + ": ");
            weights[i] = scanner.nextInt();
            values[i] = scanner.nextInt();
        }

        // Input the knapsack capacity
        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();

        // Solve the 0-1 Knapsack problem
        int maxValue = knapsack(weights, values, capacity, n);
        System.out.println("Maximum value achievable: " + maxValue);

        scanner.close();
    }
}


// Enter the number of items: 3
// Enter weight and value for item 1: 10 60
// Enter weight and value for item 2: 20 100
// Enter weight and value for item 3: 30 120
// Enter the knapsack capacity: 50

// Maximum value achievable: 220

// TC : O(n * capacity)
// SC : O(n * capacity)
